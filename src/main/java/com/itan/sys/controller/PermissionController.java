package com.itan.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itan.sys.common.Contast;
import com.itan.sys.common.DataGridView;
import com.itan.sys.common.ResultObj;
import com.itan.sys.common.TreeNode;
import com.itan.sys.entity.Permission;
import com.itan.sys.service.PermissionService;
import com.itan.sys.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itan
 * @since 2020-01-06
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    /************************权限管理开始***************************/
    /**
     * 加载权限管理左边的权限树的的json
     * @param permissionVo
     * @returns
     */
    @RequestMapping("loadPermissionJson")
    public DataGridView loadPermission(PermissionVo permissionVo){
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        //按照权限查询
        wrapper.eq("type", Contast.TYPE_MENU);
        List<Permission> list=this.permissionService.list(wrapper);
        List<TreeNode> treeNodes=new ArrayList<>();
        for (Permission permission:list){
            Boolean spread=permission.getOpen()==1?true:false;
            treeNodes.add(new TreeNode(permission.getId(),permission.getPid(),permission.getTitle(),spread));
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
        //只能查询权限
        wrapper.eq("type",Contast.TYPE_PERMISSION);
        wrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()),"title",permissionVo.getTitle());
        wrapper.like(StringUtils.isNotBlank(permissionVo.getPercode()),"percode",permissionVo.getPercode());
        wrapper.eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        wrapper.orderByAsc("ordernum");
        this.permissionService.page(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加
     * @param permissionVo
     * @return
     */
    @RequestMapping("addPermission")
    public ResultObj addPermission(PermissionVo permissionVo){
        try{
            //设置类型
            permissionVo.setType(Contast.TYPE_PERMISSION);
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
    @RequestMapping("updatePermission")
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
     * @param permissionVo
     * @return
     */
    @RequestMapping("deleteByid")
    public ResultObj deleteByid(PermissionVo permissionVo){
        try{
            System.out.println(permissionVo.getId());
            this.permissionService.removeById(permissionVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /************************权限管理结束***************************/
}
