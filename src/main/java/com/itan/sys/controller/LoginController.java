package com.itan.sys.controller;

import com.itan.sys.common.ActiverUser;
import com.itan.sys.common.ResultObj;
import com.itan.sys.common.WebUtils;
import com.itan.sys.entity.LogInfo;
import com.itan.sys.service.LogInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("login")
@RestController
public class LoginController {
    @Autowired
    private LogInfoService logInfoService;
    /**
     * 登录
     */
    @RequestMapping("/login")
    public ResultObj login(String loginname,String pwd){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginname,pwd);
        try {
            subject.login(token);
            //获取成功的信息
            ActiverUser principal = (ActiverUser) subject.getPrincipal();
            //将成功的信息存到session
            WebUtils.getSession().setAttribute("user",principal.getUser());
            //添加登录日志
            LogInfo l=new LogInfo();
            l.setLoginname(principal.getUser().getName()+"-"+principal.getUser().getLoginname());
            l.setLoginip(WebUtils.getRequest().getRemoteAddr());
            l.setLogintime(new Date());
            this.logInfoService.save(l);
            return ResultObj.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
//            e.printStackTrace();
            return ResultObj.LOGIN_ERROR_PASS;
        }
    }
}
