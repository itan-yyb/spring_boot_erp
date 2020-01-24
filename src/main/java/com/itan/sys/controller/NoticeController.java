package com.itan.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itan.sys.common.DataGridView;
import com.itan.sys.common.ResultObj;
import com.itan.sys.common.WebUtils;
import com.itan.sys.entity.Notice;
import com.itan.sys.entity.User;
import com.itan.sys.service.NoticeService;
import com.itan.sys.vo.NoticeVo;
import com.sun.xml.internal.ws.developer.Serialization;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author itan
 * @since 2020-01-08
 */
@RestController
@RequestMapping("notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 查询所有
     * @param noticeVo
     * @return
     */
    @RequestMapping("getAll")
    public DataGridView getAll(NoticeVo noticeVo){
        //分页插件
        IPage<Notice> page = new Page<>(noticeVo.getPage(),noticeVo.getLimit());
        //条件构造
        QueryWrapper<Notice> wrapper=new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(noticeVo.getTitle()),"title",noticeVo.getTitle());
        wrapper.like(StringUtils.isNotBlank(noticeVo.getOpername()),"opername",noticeVo.getOpername());
        wrapper.ge(noticeVo.getStartTime()!=null,"createtime",noticeVo.getStartTime());
        wrapper.ge(noticeVo.getEndTime()!=null,"createtime",noticeVo.getEndTime());
        wrapper.orderByDesc("createtime");
        //执行
        this.noticeService.page(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("updateNotice")
    public ResultObj updateNotice(NoticeVo noticeVo){
        try {
            this.noticeService.updateById(noticeVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 添加
     * @return
     */
    @RequestMapping("addNotice")
    public ResultObj addNotice(NoticeVo noticeVo){
        try {
            //获取当前登录人的信息
            User user = (User) WebUtils.getSession().getAttribute("user");
            //设置发布人
            noticeVo.setOpername(user.getName());
            //设置当前时间
            noticeVo.setCreatetime(new Date());
            this.noticeService.save(noticeVo);
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
    public ResultObj batchDelete(NoticeVo noticeVo){
        try {
            Collection<Serializable> collection=new ArrayList<>();
            for (Integer id:noticeVo.getIds()){
                ((ArrayList<Serializable>) collection).add(id);
            }
            this.noticeService.removeByIds(collection);
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
            this.noticeService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
}
