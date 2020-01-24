package com.itan.bus.service.impl;

import com.itan.bus.entity.Goods;
import com.itan.bus.entity.Inport;
import com.itan.bus.entity.Outport;
import com.itan.bus.mapper.GoodsMapper;
import com.itan.bus.mapper.InportMapper;
import com.itan.bus.mapper.OutportMapper;
import com.itan.bus.service.OutportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itan.sys.common.WebUtils;
import com.itan.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itan
 * @since 2020-01-21
 */
@Service
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService {
    @Autowired
    private InportMapper inportMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public void addOutport(Integer id, Integer number, String remark) {
        //1、根据进货id查询进货单信息
        Inport inport = this.inportMapper.selectById(id);
        //2、根据商品id查询商品信息
        Goods goods = this.goodsMapper.selectById(inport.getGoodsid());
        goods.setNumber(goods.getNumber()-number);
        this.goodsMapper.updateById(goods);
        //3、添加退货单信息
        Outport entity=new Outport();
        entity.setGoodsid(inport.getGoodsid());
        entity.setNumber(number);
        String name=((User) WebUtils.getSession().getAttribute("user")).getName();
        entity.setOperateperson(name);
        entity.setOutportprice(inport.getInportprice());
        entity.setOutputtime(new Date());
        entity.setPaytype(inport.getPaytype());
        entity.setProviderid(inport.getProviderid());
        entity.setRemark(remark);
        this.getBaseMapper().insert(entity);
    }
}
