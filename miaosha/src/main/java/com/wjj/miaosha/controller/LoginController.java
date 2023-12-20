package com.wjj.miaosha.controller;

import com.wjj.miaosha.service.IUserService;
import com.wjj.miaosha.vo.LoginVO;
import com.wjj.miaosha.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author: wjj
 * @CreateTime: 2023-10-16
 * @Description: 登录
 * @Version: 1.0
 */
@RestController
@RequestMapping("/login")
@Slf4j
@Api(tags = "这是一个登录功能")
public class LoginController {


    @Autowired
    private IUserService iUserService;

    /**
     * @Description:跳转登录界面
     * @Param:
     * @Return:
     */
    @RequestMapping(value = "/toLogin")
    @ApiOperation(value = "这是一个test")
    public ModelAndView toLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * @Description:登录功能
     * @Param:
     * @Return:
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) {
        return iUserService.doLogin(loginVO, request, response);
    }

}
