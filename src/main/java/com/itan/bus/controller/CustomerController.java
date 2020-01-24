package com.itan.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itan.bus.entity.Customer;
import com.itan.bus.service.CustomerService;
import com.itan.bus.vo.CustomerVo;
import com.itan.sys.common.DataGridView;
import com.itan.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itan
 * @since 2020-01-17
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    /**
     * 查询所有
     * @param customerVo
     * @return
     */
    @RequestMapping("getAll")
    public DataGridView getAll(CustomerVo customerVo){
        //分页插件
        IPage<Customer> page = new Page<>(customerVo.getPage(),customerVo.getLimit());
        //条件构造
        QueryWrapper<Customer> wrapper=new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(customerVo.getCustomername()),"customername",customerVo.getCustomername());
        wrapper.like(StringUtils.isNotBlank(customerVo.getTelephone()),"telephone",customerVo.getTelephone());
        wrapper.like(StringUtils.isNotBlank(customerVo.getConnectionperson()),"connectionperson",customerVo.getConnectionperson());
        //执行
        this.customerService.page(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("updateCustomer")
    public ResultObj updateCustomer(CustomerVo customerVo){
        try {
            this.customerService.updateById(customerVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 添加
     * @return
     */
    @RequestMapping("addCustomer")
    public ResultObj addCustomer(CustomerVo customerVo){
        try {
            this.customerService.save(customerVo);
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
    public ResultObj batchDelete(CustomerVo customerVo){
        try {
            Collection<Serializable> collection=new ArrayList<>();
            for (Integer id:customerVo.getIds()){
                ((ArrayList<Serializable>) collection).add(id);
            }
            this.customerService.removeByIds(collection);
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
            this.customerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
}
