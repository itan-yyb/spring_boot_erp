package com.itan.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("sys")
@Controller
public class SystemController {
    /**
     * 跳转到login页面
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "system/login.html";
    }
    /**
     * 登录成功跳转到首页
     */
    @RequestMapping("index")
    public String index(){
        return "system/index/index.html";
    }
    /**
     * 跳转到主控台
     */
    @RequestMapping("toMain")
    public String toMain(){
        return "system/index/main.html";
    }
    /**
     * 跳转到日志管理
     */
    @RequestMapping("toLoginfo")
    public String toLoginfo(){
        return "system/loginfo/loginfo.html";
    }
    /**
     * 跳转到公告管理
     */
    @RequestMapping("toNotice")
    public String toNotice(){
        return "system/notice/notice.html";
    }

    /**
     * 跳转到部门管理
     * @return
     */
    @RequestMapping("toDeptManager")
    public String toDeptManager(){
        return "system/dept/deptManager.html";
    }
    /**
     * 跳转到部门管理
     * @return
     */
    @RequestMapping("toDeptLeft")
    public String toDeptLeft(){
        return "system/dept/deptLeft.html";
    }
    /**
     * 跳转到部门管理
     * @return
     */
    @RequestMapping("toDeptRight")
    public String toDeptRight(){
        return "system/dept/deptRight.html";
    }
    /*********************菜单管理开始**********************/
    @RequestMapping("toMenuManager")
    public String toMenuManager(){
        return "system/menu/menuManager.html";
    }
    @RequestMapping("toMenuLeft")
    public String toMenuLeft(){
        return "system/menu/menuLeft.html";
    }
    @RequestMapping("toMenuRight")
    public String toMenuRight(){
        return "system/menu/menuRight.html";
    }
    /*********************菜单管理结束**********************/
    /*********************权限管理开始**********************/
    @RequestMapping("toPermissionManager")
    public String toPermissionManager(){
        return "system/permission/permissionManager.html";
    }
    @RequestMapping("toPermissionLeft")
    public String toPermissionLeft(){
        return "system/permission/permissionLeft.html";
    }
    @RequestMapping("toPermissionRight")
    public String toPermissionRight(){
        return "system/permission/permissionRight.html";
    }
    /*********************权限管理结束**********************/
    /*********************角色管理开始**********************/
    @RequestMapping("toRoleManager")
    public String toRoleManager(){
        return "system/role/roleManager.html";
    }
    /*********************角色管理结束**********************/
    /*********************用户管理开始**********************/
    @RequestMapping("toUserManager")
    public String toUserManager(){
        return "system/user/userManager.html";
    }
    /*********************用户管理结束**********************/
}
