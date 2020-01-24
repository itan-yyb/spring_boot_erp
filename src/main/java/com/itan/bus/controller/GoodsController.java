package com.itan.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itan.bus.entity.Goods;
import com.itan.bus.entity.Provider;
import com.itan.bus.service.GoodsService;
import com.itan.bus.service.ProviderService;
import com.itan.bus.vo.GoodsVo;
import com.itan.sys.common.AppFileUtils;
import com.itan.sys.common.Contast;
import com.itan.sys.common.DataGridView;
import com.itan.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itan
 * @since 2020-01-18
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProviderService providerService;
    /**
     * 查询所有
     * @param goodsVo
     * @return
     */
    @RequestMapping("getAll")
    public DataGridView getAll(GoodsVo goodsVo){
        //分页插件
        IPage<Goods> page = new Page<>(goodsVo.getPage(),goodsVo.getLimit());
        //条件构造
        QueryWrapper<Goods> wrapper=new QueryWrapper<>();
        wrapper.eq(goodsVo.getProviderid()!=null&&goodsVo.getProviderid()!=0,"providerid",goodsVo.getProviderid());
        wrapper.like(StringUtils.isNotBlank(goodsVo.getGoodsname()),"goodsname",goodsVo.getGoodsname());
        wrapper.like(StringUtils.isNotBlank(goodsVo.getProductcode()),"productcode",goodsVo.getProductcode());
        wrapper.like(StringUtils.isNotBlank(goodsVo.getPromitcode()),"promitcode",goodsVo.getPromitcode());
        wrapper.like(StringUtils.isNotBlank(goodsVo.getDescription()),"description",goodsVo.getDescription());
        wrapper.like(StringUtils.isNotBlank(goodsVo.getSize()),"size",goodsVo.getSize());
        //执行
        this.goodsService.page(page,wrapper);
        List<Goods> records = page.getRecords();
        for(Goods goods:records){
            //根据供应商编号查询对应的供应商
            Provider provider = this.providerService.getById(goods.getProviderid());
            if(null!=provider){
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("updateGoods")
    public ResultObj updateGoods(GoodsVo goodsVo){
        try {
            //不是默认图片需要处理
            if(!(goodsVo.getGoodsimg()!=null&&goodsVo.getGoodsimg().equals(Contast.DEFAULT_IMG_PATH))){
                if(goodsVo.getGoodsimg().endsWith("_temp")){
                    //需要改名字
                    String newName = AppFileUtils.reName(goodsVo.getGoodsimg());
                    goodsVo.setGoodsimg(newName);
                    //获取原照片路径
                    String oldPath=this.goodsService.getById(goodsVo.getId()).getGoodsimg();
                    //删除原照片
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            this.goodsService.updateById(goodsVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 添加
     * @return
     */
    @RequestMapping("addGoods")
    public ResultObj addGoods(GoodsVo goodsVo){
        try {
            if(null!=goodsVo.getGoodsimg()&&goodsVo.getGoodsimg().endsWith("_temp")){
                //需要改名字
                String newName = AppFileUtils.reName(goodsVo.getGoodsimg());
                goodsVo.setGoodsimg(newName);
            }
            this.goodsService.save(goodsVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 删除
     * @return
     */
    @RequestMapping("deleteByid")
    public ResultObj deleteByid(Integer id,String goodsimg){
        try {
            //删除原照片
            AppFileUtils.removeFileByPath(goodsimg);
            this.goodsService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载所有商品到下拉框
     * @return
     */
    @RequestMapping("loadAllGoodsForSelect")
    public DataGridView loadAllGoodsForSelect(){
        QueryWrapper<Goods> wrapper=new QueryWrapper<>();
        wrapper.eq("available",Contast.AVAILABLE_TRUE);
        List<Goods> list = this.goodsService.list(wrapper);
        for(Goods goods:list){
            //根据供应商编号查询对应的供应商
            Provider provider = this.providerService.getById(goods.getProviderid());
            if(null!=provider){
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }
    /**
     * 根据供应商id查商品信息
     * @return
     */
    @RequestMapping("loadGoodsByProviderid")
    public DataGridView loadGoodsByProviderid(Integer providerid){
        QueryWrapper<Goods> wrapper=new QueryWrapper<>();
        wrapper.eq("available",Contast.AVAILABLE_TRUE);
        wrapper.eq(providerid!=null,"providerid",providerid);
        List<Goods> list = this.goodsService.list(wrapper);
        for(Goods goods:list){
            //根据供应商编号查询对应的供应商
            Provider provider = this.providerService.getById(goods.getProviderid());
            if(null!=provider){
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }
}
