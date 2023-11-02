package com.wjj.miaosha.controller;


import com.wjj.miaosha.pojo.User;
import com.wjj.miaosha.vo.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjj
 * @since 2023-10-16
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * @Description:用户信息
     * @Param:
     * @Return:
     */
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user){
        return RespBean.success(user);
    }

}
