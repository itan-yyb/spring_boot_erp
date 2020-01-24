package com.itan.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itan.sys.common.Contast;
import com.itan.sys.common.DataGridView;
import com.itan.sys.common.ResultObj;
import com.itan.sys.common.TreeNode;
import com.itan.sys.entity.Permission;
import com.itan.sys.entity.Role;
import com.itan.sys.service.PermissionService;
import com.itan.sys.service.RoleService;
import com.itan.sys.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itan
 * @since 2020-01-12
 */
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 分页显示
     * @param roleVo
     * @return
     */
    @RequestMapping("getAll")
    public DataGridView getAll(RoleVo roleVo){
        IPage<Role> page = new Page<>(roleVo.getPage(),roleVo.getLimit());
        QueryWrapper<Role> wrapper=new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(roleVo.getName()),"name",roleVo.getName());
        wrapper.like(StringUtils.isNotBlank(roleVo.getRemark()),"remark",roleVo.getRemark());
        wrapper.eq(roleVo.getAvailable()!=null,"available",roleVo.getAvailable());
        wrapper.orderByDesc("createtime");
        this.roleService.page(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加角色
     * @param roleVo
     * @return
     */
    @RequestMapping("addRole")
    public ResultObj addRole(RoleVo roleVo){
        try {
            //设置时间
            roleVo.setCreatetime(new Date());
            //执行添加
            this.roleService.save(roleVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改
     * @param roleVo
     * @return
     */
    @RequestMapping("updateRole")
    public ResultObj updateRole(RoleVo roleVo){
        try {
            //执行修改
            this.roleService.updateById(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除角色
     * @param roleVo
     * @return
     */
    @RequestMapping("deleteByid")
    public ResultObj deleteByid(RoleVo roleVo){
        try {
            //执行修改
            this.roleService.removeById(roleVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载授权树
     * @return
     */
    @RequestMapping("loadPermissionByRoleId")
    public DataGridView loadPermissionByRoleId(@RequestParam("roleId") Integer roleId){
        //查询所有可用的菜单和权限
        QueryWrapper<Permission> wrapper=new QueryWrapper<>();
        wrapper.eq("available", Contast.AVAILABLE_TRUE);
        List<Permission> allList = this.permissionService.list(wrapper);
        /**
         * 1、根据角色ID查询当前角色拥有的所有的权限和菜单ID
         * 2、再根据查询出来的菜单ID查询权限和菜单数据
         * 根据角色ID查询当前角色拥有的权限和菜单
         */
        List<Integer> ids=this.roleService.queryRolePermissionIdsByRid(roleId);
        List<Permission> carrentPermissions=null;
        if (ids.size()>0){//如果有id就去查询
            wrapper.in("id",ids);
            carrentPermissions = permissionService.list(wrapper);
        }else {
            carrentPermissions=new ArrayList<>();
        }
        //构造List<TreeNode>
        List<TreeNode> nodes = new ArrayList<>();
        for (Permission p1:allList){
            String checkArr="0";
            for(Permission p2:carrentPermissions){
                if(p1.getId()==p2.getId()){
                    checkArr="1";
                    break;
                }
            }
            Boolean spread=(p1.getOpen()==null||p1.getOpen()==1)?true:false;
            nodes.add(new TreeNode(p1.getId(),p1.getPid(),p1.getTitle(),spread,checkArr));
        }
        return new DataGridView(nodes);
    }

    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param ids
     * @return
     */
    @RequestMapping("savePermissionRole")
    public ResultObj savePermissionRole(Integer rid,Integer[]ids){
        try {
            this.roleService.savePermissionRole(rid,ids);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }
}
