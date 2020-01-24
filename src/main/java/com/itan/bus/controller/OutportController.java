package com.itan.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itan.bus.entity.Goods;
import com.itan.bus.entity.Inport;
import com.itan.bus.entity.Outport;
import com.itan.bus.entity.Provider;
import com.itan.bus.service.GoodsService;
import com.itan.bus.service.InportService;
import com.itan.bus.service.OutportService;
import com.itan.bus.service.ProviderService;
import com.itan.bus.vo.InportVo;
import com.itan.bus.vo.OutportVo;
import com.itan.sys.common.DataGridView;
import com.itan.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itan
 * @since 2020-01-21
 */
@RestController
@RequestMapping("outport")
public class OutportController {
    @Autowired
    private OutportService outportService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private GoodsService goodsService;
    /**
     * 查询所有
     * @param outportVo
     * @return
     */
    @RequestMapping("getAll")
    public DataGridView getAll(OutportVo outportVo){
        //分页插件
        IPage<Outport> page = new Page<>(outportVo.getPage(),outportVo.getLimit());
        //条件构造
        QueryWrapper<Outport> wrapper=new QueryWrapper<>();
        wrapper.eq(outportVo.getProviderid()!=null&&outportVo.getProviderid()!=0,"providerid",outportVo.getProviderid());
        wrapper.eq(outportVo.getGoodsid()!=null&&outportVo.getGoodsid()!=0,"goodsid",outportVo.getGoodsid());
        wrapper.ge(outportVo.getStartTime()!=null,"outputtime",outportVo.getStartTime());
        wrapper.le(outportVo.getEndTime()!=null,"outputtime",outportVo.getEndTime());
        wrapper.like(StringUtils.isNotBlank(outportVo.getOperateperson()),"operateperson",outportVo.getOperateperson());
        wrapper.like(StringUtils.isNotBlank(outportVo.getRemark()),"remark",outportVo.getRemark());
        wrapper.orderByDesc("outputtime");
        //执行
        this.outportService.page(page,wrapper);
        List<Outport> records = page.getRecords();
        for(Outport outport:records){
            //根据供应商编号查询对应的供应商
            Provider provider = this.providerService.getById(outport.getProviderid());
            if(null!=provider){
                outport.setProvidername(provider.getProvidername());
            }
            //根据商品id从缓存中查询商品名
            Goods good = this.goodsService.getById(outport.getGoodsid());
            if (null!=good) {
                outport.setGoodsname(good.getGoodsname());
                outport.setSize(good.getSize());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }
    /**
     * 退货
     * @param id
     * @param number
     * @param remark
     * @return
     */
    @RequestMapping("addOutport")
    public ResultObj addOutport(Integer id,Integer number,String remark){
        try{
            this.outportService.addOutport(id,number,remark);
            return ResultObj.OPERATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.OPERATE_ERROR;
        }
    }
}
