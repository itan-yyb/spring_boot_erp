package com.itan.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itan.bus.entity.Provider;
import com.itan.bus.service.ProviderService;
import com.itan.bus.vo.ProviderVo;
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
 * @since 2020-01-17
 */
@RestController
@RequestMapping("provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    /**
     * 查询所有
     * @param providerVo
     * @return
     */
    @RequestMapping("getAll")
    public DataGridView getAll(ProviderVo providerVo){
        //分页插件
        IPage<Provider> page = new Page<>(providerVo.getPage(),providerVo.getLimit());
        //条件构造
        QueryWrapper<Provider> wrapper=new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(providerVo.getProvidername()),"providername",providerVo.getProvidername());
        wrapper.like(StringUtils.isNotBlank(providerVo.getTelephone()),"telephone",providerVo.getTelephone());
        wrapper.like(StringUtils.isNotBlank(providerVo.getConnectionperson()),"connectionperson",providerVo.getConnectionperson());
        //执行
        this.providerService.page(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("updateProvider")
    public ResultObj updateProvider(ProviderVo providerVo){
        try {
            this.providerService.updateById(providerVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 添加
     * @return
     */
    @RequestMapping("addProvider")
    public ResultObj addProvider(ProviderVo providerVo){
        try {
            this.providerService.save(providerVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 批量删除
     * @return
     */
    @RequestMapping("batchDelete")
    public ResultObj batchDelete(ProviderVo providerVo){
        try {
            Collection<Serializable> collection=new ArrayList<>();
            for (Integer id:providerVo.getIds()){
                ((ArrayList<Serializable>) collection).add(id);
            }
            this.providerService.removeByIds(collection);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 删除
     * @return
     */
    @RequestMapping("deleteByid")
    public ResultObj deleteByid(Integer id){
        try {
            this.providerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载供应商下拉列表
     * @return
     */
    @RequestMapping("loadAllProviderForSelect")
    public DataGridView loadAllProviderForSelect(){
        QueryWrapper<Provider> wrapper=new QueryWrapper<>();
        wrapper.eq("available", Contast.AVAILABLE_TRUE);
        List<Provider> list = this.providerService.list(wrapper);
        return new DataGridView(list);
    }
}
