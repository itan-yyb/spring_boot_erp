package com.itan.bus.service.impl;

import com.itan.bus.entity.Goods;
import com.itan.bus.entity.Inport;
import com.itan.bus.mapper.GoodsMapper;
import com.itan.bus.mapper.InportMapper;
import com.itan.bus.service.GoodsService;
import com.itan.bus.service.InportService;
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
 * @since 2020-01-21
 */
@Service
@Transactional
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService {
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 添加
     * @param entity
     * @return
     */
    @Override
    public boolean save(Inport entity) {
        //根据商品编号查询商品
        Goods goods=goodsMapper.selectById(entity.getGoodsid());
        //原有库存+进货数量=库存总量
        goods.setNumber(goods.getNumber()+entity.getNumber());
        goodsMapper.updateById(goods);
        return super.save(entity);
    }

    /**
     * 修改
     * @param entity
     * @return
     */
    @Override
    public boolean updateById(Inport entity) {
        //根据进货id查询进货
        Inport inport = this.baseMapper.selectById(entity.getGoodsid());
        //根据商品id查询商品信息
        Goods goods = this.goodsMapper.selectById(entity.getGoodsid());
        //对库存计算 当前库存-进货单修改之前的数量+修改后的数量
        goods.setNumber(goods.getNumber()-inport.getNumber()+entity.getNumber());
        //修改商品信息
        this.goodsMapper.updateById(goods);
        //修改进货信息
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        //根据进货id查询进货
        Inport inport = this.baseMapper.selectById(id);
        //根据商品id查询商品信息
        Goods goods = this.goodsMapper.selectById(inport.getGoodsid());
        //对库存计算 当前库存-进货单数量
        goods.setNumber(goods.getNumber()-inport.getNumber());
        this.goodsMapper.updateById(goods);
        return super.removeById(id);
    }
}
