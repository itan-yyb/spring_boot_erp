package com.itan.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itan.sys.common.DataGridView;
import com.itan.sys.common.ResultObj;
import com.itan.sys.entity.LogInfo;
import com.itan.sys.service.LogInfoService;
import com.itan.sys.vo.LoginfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * <p>
 *  登录日志
 * </p>
 *
 * @author itan
 * @since 2020-01-07
 */
@RestController
@RequestMapping("/loginfo")
public class LogInfoController {
    @Autowired
    private LogInfoService logInfoService;

    /**
     * 分页显示
     * @param loginfoVo
     * @return
     */
    @RequestMapping("loginAll")
    public DataGridView logAll(LoginfoVo loginfoVo){
        IPage<LogInfo> page = new Page<>(loginfoVo.getPage(),loginfoVo.getLimit());
        QueryWrapper<LogInfo> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginname()),"loginname",loginfoVo.getLoginname());
        wrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginip()),"loginip",loginfoVo.getLoginip());
        //大于等于
        wrapper.ge(loginfoVo.getStartTime()!=null,"logintime",loginfoVo.getStartTime());
        System.out.println();
        //小于等于
        wrapper.le(loginfoVo.getEndTime()!=null,"logintime",loginfoVo.getEndTime());
        //排序
        wrapper.orderByDesc("logintime");
        this.logInfoService.page(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("deleteByid")
    public ResultObj deleteLoginfo(Integer id){
        try {
            this.logInfoService.removeById(id);
            //删除成功
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            //删除失败
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除
     * @param loginfoVo
     * @return
     */
    @RequestMapping("batchDelete")
    public ResultObj batchdeleteLoginfo(LoginfoVo loginfoVo){
        try {
            Collection<Serializable> collection= new ArrayList<Serializable>();
            for (Integer id:loginfoVo.getIds()){
                ((ArrayList<Serializable>) collection).add(id);
            }
            this.logInfoService.removeByIds(collection);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
