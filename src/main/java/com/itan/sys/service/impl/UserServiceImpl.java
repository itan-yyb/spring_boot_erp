package com.itan.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.itan.sys.entity.User;
import com.itan.sys.mapper.RoleMapper;
import com.itan.sys.mapper.UserMapper;
import com.itan.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itan
 * @since 2020-01-05
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private RoleMapper roleMapper;
    /**
     * 添加
     * @param entity
     * @return
     */
    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    /**
     * 修改
     * @param entity
     * @return
     */
    @Override
    public boolean updateById(User entity) {
        return super.updateById(entity);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        //根据用户id删除用户角色中间表的数据
        this.roleMapper.deleteRoleUserByUid(id);
        //删除用户头像【默认图像不删除】

        return super.removeById(id);
    }

    /**
     * 查询一条数据
     * @param id
     * @return
     */
    @Override
    public User getById(Serializable id) {
        return super.getById(id);
    }

    /**
     * 保存用户和角色的权限
     * @param uid
     * @param ids
     */
    @Override
    public void saveUserRole(Integer uid, Integer[] ids) {
        //先根据用户ID删除sys_role_user的数据
        this.roleMapper.deleteRoleUserByUid(uid);
        //判断
        if(ids!=null&&ids.length>0){
            for(Integer rid:ids){
                this.getBaseMapper().saveUserRole(uid,rid);
            }
        }
    }
}
