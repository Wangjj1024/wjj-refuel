package com.wjj.miaosha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wjj
 * @CreateTime: 2023-10-15
 * @Description: 测试
 * @Version: 1.0
 */

@Controller
@RequestMapping("/demo")
public class DemoController {


    /**
     * 功能描述: 测试页面跳转
     * @param model
     * @return
     */
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "xxxx");
        return "hello";
    }
}
