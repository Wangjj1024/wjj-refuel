package com.wjj.miaosha.controller;


import com.wjj.miaosha.pojo.User;
import com.wjj.miaosha.service.IOrderService;
import com.wjj.miaosha.vo.OrderDetailVO;
import com.wjj.miaosha.vo.RespBean;
import com.wjj.miaosha.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjj
 * @since 2023-10-23
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;
    /**
     * @Description:订单详情
     * @Param:
     * @Return:
     */
    @RequestMapping("/detail")
    @ResponseBody
    public RespBean detail(User user, Long orderId){
        if (user == null){
            return RespBean.error(RespBeanEnum.SESSION_ERRRO);
        }
        OrderDetailVO orderDetailVO = orderService.detail(orderId);
        return RespBean.success(orderDetailVO);
    }
}
