package com.itan.sys.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itan.sys.common.Contast;
import com.itan.sys.common.DataGridView;
import com.itan.sys.common.PinyinUtils;
import com.itan.sys.common.ResultObj;
import com.itan.sys.entity.Dept;
import com.itan.sys.entity.Role;
import com.itan.sys.entity.User;
import com.itan.sys.service.DeptService;
import com.itan.sys.service.RoleService;
import com.itan.sys.service.UserService;
import com.itan.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itan
 * @since 2020-01-05
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;
    /**
     * 用户全查询
     */
    @RequestMapping("getAll")
    public DataGridView getAll(UserVo userVo){
        IPage<User> page = new Page<>(userVo.getPage(),userVo.getLimit());
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //根据用户姓名查询
        wrapper.eq(StringUtils.isNotBlank(userVo.getName()),"name",userVo.getName()).or().eq(StringUtils.isNotBlank(userVo.getName()),"loginname",userVo.getName());
        //根据用户地址查询
        wrapper.eq(StringUtils.isNotBlank(userVo.getAddress()),"address",userVo.getAddress());
        //超级管理员查询所有系统用户
        wrapper.eq("type", Contast.USER_TYPE_NORMAL);
        //根据部门id查询
        wrapper.eq(userVo.getDeptid()!=null,"deptid",userVo.getDeptid());
        this.userService.page(page,wrapper);
        List<User> list = page.getRecords();
        for(User user:list){
            Integer deptid = user.getDeptid();//部门id
            if(deptid!=null){
                //根据部门id查询部门名称
                Dept one = this.deptService.getById(deptid);
                user.setDeptname(one.getTitle());//获取部门名称
            }
            //获取当前用户id的上级的id
            Integer mgr = user.getMgr();
            if(mgr!=null){
                //查询上级的信息
                User one = this.userService.getById(mgr);
                user.setLeadername(one.getName());//获取上级的名称
            }
        }
        return new DataGridView(page.getTotal(),list);
    }
    /**
     * 查询最大排序码
     * @return
     */
    @RequestMapping("loadMax")
    public Map<String,Object> loadMax(){
        Map<String,Object> map = new HashMap<String,Object>();
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("ordernum");
        List<User> list = this.userService.list(wrapper);
        if (list.size()>0){
            map.put("value",list.get(0).getOrdernum()+1);
        }else {
            map.put("value",1);
        }
        return map;
    }

    /**
     * 根据部门id获取用户信息
     * @param deptid
     * @return
     */
    @RequestMapping("getLeaderName")
    public DataGridView getLeaderName(Integer deptid){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(deptid!=null, "deptid", deptid);
        queryWrapper.eq("available", Contast.AVAILABLE_TRUE);
        queryWrapper.eq("type", Contast.USER_TYPE_NORMAL);
        List<User> list = this.userService.list(queryWrapper);
        return new DataGridView(list);
    }

    /**
     * 将中文名称转化为拼音形式
     * @return
     */
    @RequestMapping("changeChineseToPinyin")
    public Map<String,Object> changeChineseToPinyin(String name){
        Map<String,Object> map = new HashMap<>();
        //调用拼音工具类的方法
        if(name!=null){
            String pingYinName = PinyinUtils.getPingYin(name);
            map.put("value",pingYinName);
        }else{
            map.put("value","");
        }
        return map;
    }

    /**
     * 添加
     * @param userVo
     * @return
     */
    @RequestMapping("addUser")
    public ResultObj addUser(UserVo userVo){
        try {
            //设置时间
            userVo.setHiredate(new Date());
            //设置类型
            userVo.setType(Contast.USER_TYPE_NORMAL);
            //设置盐
            String salt= IdUtil.simpleUUID().toUpperCase();
            userVo.setSalt(salt);
            //设置密码
            userVo.setPwd(new Md5Hash(Contast.USER_DEFAULT_PWD,salt,2).toString());
            this.userService.save(userVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据id查询领导部门的id，并选中
     * @param id
     * @return
     */
    @RequestMapping("loadUserById")
    public DataGridView loadUserById(Integer id){
        return new DataGridView(this.userService.getById(id));
    }
    /**
     * 修改
     * @param userVo
     * @return
     */
    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo) {
        try {
            this.userService.updateById(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
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
        try {
            this.userService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("resetPwd")
    public ResultObj resetPwd(Integer id){
        try{
            User user=new User();
            user.setId(id);
            //设置盐
            String salt= IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);
            //设置密码
            user.setPwd(new Md5Hash(Contast.USER_DEFAULT_PWD,salt,2).toString());
            this.userService.updateById(user);
            return ResultObj.RESET_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 根据用户id查询角色并选中已拥有角色
     * @return
     */
    @RequestMapping("initRoleByUserId")
    public DataGridView initRoleByUserId(Integer id){
        //查询所有可用的角色
        QueryWrapper<Role> wrapper=new QueryWrapper<>();
        wrapper.eq("available",Contast.AVAILABLE_TRUE);
        List<Map<String, Object>> maps = this.roleService.listMaps(wrapper);
        //查询当前用户id都拥有的角色ID集合
        List<Integer> ids=this.roleService.queryUserRoleIdsByUid(id);

        for (Map<String,Object> map:maps){
            Boolean LAY_CHECKED=false;
            Integer roleId= (Integer) map.get("id");
            for (Integer rid:ids){
                if(roleId==rid){
                    LAY_CHECKED=true;
                    break;
                }
            }
            map.put("LAY_CHECKED",LAY_CHECKED);
        }
        return new DataGridView(Long.valueOf(maps.size()),maps);
    }

    /**
     * 保存用户和角色的权限
     * @return
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(Integer uid,Integer[] ids){
        try {
            this.userService.saveUserRole(uid,ids);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
}
