package com.wjj.miaosha.controller;

import com.wjj.miaosha.service.IUserService;
import com.wjj.miaosha.vo.LoginVO;
import com.wjj.miaosha.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author: wjj
 * @CreateTime: 2023-10-16
 * @Description: 登录
 * @Version: 1.0
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {


    @Autowired
    private IUserService iUserService;

    /**
     * @Description:跳转登录界面
     * @Param:
     * @Return:
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
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
