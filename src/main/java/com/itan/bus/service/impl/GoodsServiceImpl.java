package com.itan.bus.service.impl;

import com.itan.bus.entity.Goods;
import com.itan.bus.mapper.GoodsMapper;
import com.itan.bus.service.GoodsService;
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
 * @since 2020-01-18
 */
@Service
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Override
    public boolean save(Goods entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Goods entity) {
        return super.updateById(entity);
    }

    @Override
    public Goods getById(Serializable id) {
        return super.getById(id);
    }
}
