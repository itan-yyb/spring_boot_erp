package com.itan.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itan.sys.common.*;
import com.itan.sys.entity.Permission;
import com.itan.sys.entity.User;
import com.itan.sys.service.PermissionService;
import com.itan.sys.service.RoleService;
import com.itan.sys.service.UserService;
import com.itan.sys.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 * <p>
 *  菜单管理前端控制器
 * </p>
 *
 * @author itan
 * @since 2020-01-06
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("loadMenu")
    public DataGridView loadIndexLeftMenuJson(Permission permission){
        //查询所有菜单
        QueryWrapper<Permission> wrapper=new QueryWrapper<>();
        //只查菜单字段
        wrapper.eq("type", Contast.TYPE_MENU);
        wrapper.eq("available", Contast.AVAILABLE_TRUE);
        //获取用户信息，判断是否为超管
        User user = (User)WebUtils.getSession().getAttribute("user");
        List<Permission> list = null;
        //判断
        if(user.getType()==Contast.USER_TYPE_SUPER){
            list = permissionService.list(wrapper);
        } else {
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
            //根据角色id查询权限
            if(pids.size()>0){
                wrapper.in("id",pids);
                list=this.permissionService.list(wrapper);
            }else{
                list=new ArrayList<>();
            }
        }
        List<TreeNode> treeNodes=new ArrayList<>();
        for (Permission p:list){
            treeNodes.add(new TreeNode(p.getId(),p.getPid(),p.getTitle(),p.getIcon(),p.getHref(),p.getOpen()==Contast.OPEN_TRUE?true:false));
        }
        //构造层级关系
        List<TreeNode> list2= TreeNodeBuilder.build(treeNodes,1);
        return new DataGridView(list2);
    }
    /************************菜单管理开始***************************/
    /**
     * 加载菜单管理左边的菜单树的的json
     * @param permissionVo
     * @returns
     */
    @RequestMapping("loadMenuJson")
    public DataGridView loadPermission(PermissionVo permissionVo){
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        //按照菜单查询
        wrapper.eq("type",Contast.TYPE_MENU);
        List<Permission> list=this.permissionService.list(wrapper);
        List<TreeNode> treeNodes=new ArrayList<>();
        for (Permission menu:list){
            Boolean spread=menu.getOpen()==1?true:false;
            treeNodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),spread));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询最大排序码
     * @return
     */
    @RequestMapping("loadMax")
    public Map<String,Object> loadMax(){
        Map<String,Object> map = new HashMap<String,Object>();
        QueryWrapper<Permission> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("ordernum");
        List<Permission> list = this.permissionService.list(wrapper);
        if (list.size()>0){
            map.put("value",list.get(0).getOrdernum()+1);
        }else {
            map.put("value",1);
        }
        return map;
    }
    /**
     * 查询所有
     * @param permissionVo
     * @return
     */
    @RequestMapping("getAll")
    public DataGridView getAll(PermissionVo permissionVo){
        IPage<Permission> page = new Page<>(permissionVo.getPage(),permissionVo.getLimit());
        QueryWrapper<Permission> wrapper=new QueryWrapper<>();
        //只能查询菜单
        wrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()),"title",permissionVo.getTitle());
        wrapper.eq(permissionVo.getId()!=null,"id",permissionVo.getId()).or().eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        wrapper.orderByAsc("ordernum");
        wrapper.eq("type",Contast.TYPE_MENU);
        this.permissionService.page(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加
     * @param permissionVo
     * @return
     */
    @RequestMapping("addMenu")
    public ResultObj addPermission(PermissionVo permissionVo){
        try{
            //设置类型
            permissionVo.setType(Contast.TYPE_MENU);
            this.permissionService.save(permissionVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改
     * @param permissionVo
     * @return
     */
    @RequestMapping("updateMenu")
    public ResultObj updatePermission(PermissionVo permissionVo){
        try{
            this.permissionService.updateById(permissionVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("deleteByid")
    public ResultObj deleteByid(Integer id){
        try{
            this.permissionService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 检查每个菜单下是否存在子菜单
     * @return
     */
    @RequestMapping("checkMenuHasChildrenNode")
    public Map<String,Object> checkPermissionHasChildrenNode(PermissionVo permissionVo){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Permission> wrapper=new QueryWrapper<>();
        wrapper.eq("pid",permissionVo.getId());
        List<Permission> list = this.permissionService.list(wrapper);
        if(list.size()>0){
            map.put("value",true);
        }else{
            map.put("value",false);
        }
        return map;
    }
    /************************菜单管理结束***************************/
}
