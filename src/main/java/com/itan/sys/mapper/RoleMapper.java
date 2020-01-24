package com.itan.sys.mapper;

import com.itan.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author itan
 * @since 2020-01-12
 */
public interface RoleMapper extends BaseMapper<Role> {
    //根据角色id删除sys_role_permission
    void deleteRolePermissionByRid(@Param("id") Serializable id);
    //根据角色id删除sys_role_user
    void deleteRoleUserByRid(@Param("id") Serializable id);
    //根据角色ID查询当前角色拥有的所有的权限和菜单ID
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);
    //保存角色和菜单权限之间的关系
    void savePermissionRole(@Param("rid") Integer rid, @Param("pid") Integer pid);
    //根据用户id删除用户角色中间表的数据
    void deleteRoleUserByUid(@Param("uid") Serializable uid);
    //查询当前用户id都拥有的角色ID集合
    List<Integer> queryUserRoleIdsByUid(Integer id);
}
