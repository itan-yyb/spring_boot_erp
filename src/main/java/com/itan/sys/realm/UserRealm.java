package com.itan.sys.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itan.sys.common.ActiverUser;
import com.itan.sys.common.Contast;
import com.itan.sys.entity.Permission;
import com.itan.sys.entity.User;
import com.itan.sys.service.PermissionService;
import com.itan.sys.service.RoleService;
import com.itan.sys.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    @Lazy
    private PermissionService permissionService;
    @Autowired
    @Lazy
    private RoleService roleService;

    @Override
    public String getName(){
        return this.getClass().getSimpleName();
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pr) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        ActiverUser activerUser=(ActiverUser) pr.getPrimaryPrincipal();
        User user = activerUser.getUser();
        List<String> permissions=activerUser.getPermissions();
        if(user.getType()==Contast.USER_TYPE_SUPER){
            //超级管理的所有权限
            info.addStringPermission("*:*");
        }else{
            if(permissions!=null&&permissions.size()>0){
                info.addStringPermissions(permissions);
            }
        }
        return info;
    }
    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //用户名
        wrapper.eq("loginname",token.getPrincipal().toString());
        User user = this.userService.getOne(wrapper);
        //判断
        if(null!=user){
            ActiverUser activerUser = new ActiverUser();
            activerUser.setUser(user);
            //根据用户id查询权限编码
            //查询所有菜单
            QueryWrapper<Permission> wr=new QueryWrapper<>();
            //只查菜单字段
            wr.eq("type", Contast.TYPE_PERMISSION);
            wr.eq("available", Contast.AVAILABLE_TRUE);
            //普通用户要根据用户ID+角色+权限查询
            Integer userId=user.getId();
            //根据用户id查询角色
            List<Integer> roleIds = this.roleService.queryUserRoleIdsByUid(userId);
            //根据角色id取到权限和菜单id
            Set<Integer> pids=new HashSet<>();//去除重复的pid
            for (Integer rid:roleIds){
                List<Integer> permissionIds=roleService.queryRolePermissionIdsByRid(rid);
                pids.addAll(permissionIds);
            }
            List<Permission> list=new ArrayList<>();
            //根据角色id查询权限
            if(pids.size()>0){
                wr.in("id",pids);
                list=this.permissionService.list(wr);
            }
            List<String> percodes=new ArrayList<>();
            for (Permission permission:list){
                percodes.add(permission.getPercode());
            }
            //放入
            activerUser.setPermissions(percodes);
            //加盐
            ByteSource salt=ByteSource.Util.bytes(user.getSalt());
            //将从数据库查询到的信息封装到SimpleAuthenticationInfo中
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser,user.getPwd(),salt,this.getName());
            return info;
        }
        return null;
    }
}
