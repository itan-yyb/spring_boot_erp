package com.itan.sys.mapper;

import com.itan.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itan
 * @since 2020-01-05
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 保存角色和用户的关系
     * @param uid
     * @param rid
     */
    void saveUserRole(@Param("uid") Integer uid, @Param("rid") Integer rid);
}
