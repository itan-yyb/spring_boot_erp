package com.itan.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itan.sys.common.DataGridView;
import com.itan.sys.common.ResultObj;
import com.itan.sys.common.TreeNode;
import com.itan.sys.entity.Dept;
import com.itan.sys.service.DeptService;
import com.itan.sys.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itan
 * @since 2020-01-09
 */
@RestController
@RequestMapping("dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    /**
     * 加载部门管理左边的部门树的的json
     * @param deptVo
     * @returns
     */
    @RequestMapping("loadDept")
    public DataGridView loadDept(DeptVo deptVo){
        List<Dept> list=this.deptService.list();
        List<TreeNode> treeNodes=new ArrayList<>();
        for (Dept dept:list){
            Boolean spread=dept.getOpen()==1?true:false;
            treeNodes.add(new TreeNode(dept.getId(),dept.getPid(),dept.getTitle(),spread));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询最大排序码
     * @return
     */
    @RequestMapping("loadMax")
    public Map<String,Object> loadMax(){
        Map<String,Object> map = new HashMap<String,Object>();
        QueryWrapper<Dept> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("ordernum");
        List<Dept> list = this.deptService.list(wrapper);
        if (list.size()>0){
            map.put("value",list.get(0).getOrdernum()+1);
        }else {
            map.put("value",1);
        }
        return map;
    }
    /**
     * 查询所有
     * @param deptVo
     * @return
     */
    @RequestMapping("getAll")
    public DataGridView getAll(DeptVo deptVo){
        IPage<Dept> page = new Page<>(deptVo.getPage(),deptVo.getLimit());
        QueryWrapper<Dept> wrapper=new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(deptVo.getTitle()),"title",deptVo.getTitle());
        wrapper.like(StringUtils.isNotBlank(deptVo.getAddress()),"address",deptVo.getAddress());
        wrapper.like(StringUtils.isNotBlank(deptVo.getRemark()),"remark",deptVo.getRemark());
        wrapper.eq(deptVo.getId()!=null,"id",deptVo.getId()).or().eq(deptVo.getId()!=null,"pid",deptVo.getId());
        wrapper.orderByAsc("ordernum");
        this.deptService.page(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加
     * @param deptVo
     * @return
     */
    @RequestMapping("addDept")
    public ResultObj addDept(DeptVo deptVo){
        try{
            //设置时间
            deptVo.setCreatetime(new Date());
            this.deptService.save(deptVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改
     * @param deptVo
     * @return
     */
    @RequestMapping("updateDept")
    public ResultObj updateDept(DeptVo deptVo){
        try{
            //设置时间
            deptVo.setCreatetime(new Date());
            this.deptService.updateById(deptVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
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
        try{
            this.deptService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 检查每个部门下是否存在子部门
     * @return
     */
    @RequestMapping("checkDeptHasChildrenNode")
    public Map<String,Object> checkDeptHasChildrenNode(DeptVo deptVo){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Dept> wrapper=new QueryWrapper<>();
        wrapper.eq("pid",deptVo.getId());
        List<Dept> list = this.deptService.list(wrapper);
        if(list.size()>0){
            map.put("value",true);
        }else{
            map.put("value",false);
        }
        return map;
    }
}
