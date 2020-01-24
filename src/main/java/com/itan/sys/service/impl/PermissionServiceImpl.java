package com.itan.sys.service.impl;

import com.itan.sys.entity.Permission;
import com.itan.sys.mapper.PermissionMapper;
import com.itan.sys.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itan
 * @since 2020-01-06
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Override
    public boolean removeById(Serializable id) {
        //根据权限或菜单ID删除权限表和角色的关系表里面的数据
        PermissionMapper baseMapper = this.getBaseMapper();
        baseMapper.deleteRolePermissionByPid(id);
        return super.removeById(id);//删除权限表的数据
    }
}