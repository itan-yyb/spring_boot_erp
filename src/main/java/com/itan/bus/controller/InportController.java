package com.itan.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itan.bus.entity.Goods;
import com.itan.bus.entity.Inport;
import com.itan.bus.entity.Provider;
import com.itan.bus.service.GoodsService;
import com.itan.bus.service.InportService;
import com.itan.bus.service.InportService;
import com.itan.bus.service.ProviderService;
import com.itan.bus.vo.InportVo;
import com.itan.sys.common.DataGridView;
import com.itan.sys.common.ResultObj;
import com.itan.sys.common.WebUtils;
import com.itan.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
@RequestMapping("inport")
public class InportController {
    @Autowired
    private InportService inportService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private GoodsService goodsService;
    /**
     * 查询所有
     * @param inportVo
     * @return
     */
    @RequestMapping("getAll")
    public DataGridView getAll(InportVo inportVo){
        //分页插件
        IPage<Inport> page = new Page<>(inportVo.getPage(),inportVo.getLimit());
        //条件构造
        QueryWrapper<Inport> wrapper=new QueryWrapper<>();
        wrapper.eq(inportVo.getProviderid()!=null&&inportVo.getProviderid()!=0,"providerid",inportVo.getProviderid());
        wrapper.eq(inportVo.getGoodsid()!=null&&inportVo.getGoodsid()!=0,"goodsid",inportVo.getGoodsid());
        wrapper.ge(inportVo.getStartTime()!=null,"inporttime",inportVo.getStartTime());
        wrapper.le(inportVo.getEndTime()!=null,"inporttime",inportVo.getEndTime());
        wrapper.like(StringUtils.isNotBlank(inportVo.getOperateperson()),"operateperson",inportVo.getOperateperson());
        wrapper.like(StringUtils.isNotBlank(inportVo.getRemark()),"remark",inportVo.getRemark());
        wrapper.orderByDesc("inporttime");
        //执行
        this.inportService.page(page,wrapper);
        List<Inport> records = page.getRecords();
        for(Inport inport:records){
            //根据供应商编号查询对应的供应商
            Provider provider = this.providerService.getById(inport.getProviderid());
            if(null!=provider){
                inport.setProvidername(provider.getProvidername());
            }
            //根据商品id从缓存中查询商品名
            Goods good = this.goodsService.getById(inport.getGoodsid());
            if (null!=good) {
                inport.setGoodsname(good.getGoodsname());
                inport.setSize(good.getSize());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }

    /**
     * 添加
     * @param inportVo
     * @return
     */
    @RequestMapping("addInport")
    public ResultObj addInport(InportVo inportVo){
        try {
            //设置时间
            inportVo.setInporttime(new Date());
            //获取当前登录人
            User user = (User) WebUtils.getSession().getAttribute("user");
            //设置操作人
            inportVo.setOperateperson(user.getName());
            this.inportService.save(inportVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改
     * @param inportVo
     * @return
     */
    @RequestMapping("updateInport")
    public ResultObj updateInport(InportVo inportVo){
        try {
            this.inportService.updateById(inportVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("deleteByid")
    public ResultObj deleteByid(Integer id){
        try {
            this.inportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
