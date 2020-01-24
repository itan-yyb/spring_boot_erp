package com.itan.sys.service;

import com.itan.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itan
 * @since 2020-01-05
 */
public interface UserService extends IService<User> {
    //保存用户和角色的权限
    void saveUserRole(Integer uid, Integer[] ids);
}
