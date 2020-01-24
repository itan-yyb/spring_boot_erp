package com.itan.sys.service.impl;

import com.itan.sys.entity.Role;
import com.itan.sys.mapper.RoleMapper;
import com.itan.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itan
 * @since 2020-01-12
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public boolean removeById(Serializable id) {
        //根据角色id删除sys_role_permission
        this.getBaseMapper().deleteRolePermissionByRid(id);
        //根据角色id删除sys_role_user
        this.getBaseMapper().deleteRoleUserByRid(id);
        return super.removeById(id);
    }

    /**
     * 根据角色ID查询当前角色拥有的所有的权限和菜单ID
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> queryRolePermissionIdsByRid(Integer roleId) {
        return this.getBaseMapper().queryRolePermissionIdsByRid(roleId);
    }
    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param ids
     */
    @Override
    public void savePermissionRole(Integer rid, Integer[] ids) {
        RoleMapper roleMapper = this.getBaseMapper();
        //根据rid删除sys_role_permission中存在的权限
        roleMapper.deleteRolePermissionByRid(rid);
        if(ids!=null&&ids.length>0){
            for (Integer pid:ids){
                roleMapper.savePermissionRole(rid,pid);
            }
        }
    }

    /**
     * 查询当前用户id都拥有的角色ID集合
     * @param id
     * @return
     */
    @Override
    public List<Integer> queryUserRoleIdsByUid(Integer id) {
        return this.getBaseMapper().queryUserRoleIdsByUid(id);
    }
}
