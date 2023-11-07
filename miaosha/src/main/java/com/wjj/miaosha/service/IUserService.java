package com.wjj.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjj.miaosha.pojo.User;
import com.wjj.miaosha.vo.LoginVO;
import com.wjj.miaosha.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wjj
 * @since 2023-10-16
 */
public interface IUserService extends IService<User> {

    /**
     * @Description: 登录
     * @Param:
     * @Return:
     */
    RespBean doLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response);

    /**
     * @Description:根据cookie获取用户
     * @Param:
     * @Return:
     */
    User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);

    /**
     * @Description: 更新密码
     * @Param:
     * @Return:
     */
    RespBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response);
}
