package com.itan.sys.service;

import com.itan.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itan
 * @since 2020-01-12
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据角色id查询当前角色拥有的所有权限或菜单的id
     * @param roleId
     * @return
     */
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);
    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param ids
     */
    void savePermissionRole(Integer rid, Integer[] ids);

    /**
     * 查询当前用户id都拥有的角色ID集合
     * @param id
     * @return
     */
    List<Integer> queryUserRoleIdsByUid(Integer id);
}
