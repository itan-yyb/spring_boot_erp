package com.itan.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.itan.sys.entity.Dept;
import com.itan.sys.mapper.DeptMapper;
import com.itan.sys.service.DeptService;
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
 * @since 2020-01-09
 */
@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public Dept getById(Serializable id) {
        return super.getById(id);
    }

    /**
     * 修改
     * @param entity
     * @return
     */
    @Override
    public boolean updateById(Dept entity) {
        return super.updateById(entity);
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    /**
     * 添加
     * @param entity
     * @return
     */
    @Override
    public boolean save(Dept entity) {
        return super.save(entity);
    }
}
