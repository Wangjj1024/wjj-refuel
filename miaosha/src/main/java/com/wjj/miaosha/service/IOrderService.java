package com.wjj.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjj.miaosha.pojo.Order;
import com.wjj.miaosha.pojo.User;
import com.wjj.miaosha.vo.GoodsVO;
import com.wjj.miaosha.vo.OrderDetailVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjj
 * @since 2023-10-21
 */
public interface IOrderService extends IService<Order> {

    /**
     * @Description:秒杀
     * @Param:
     * @Return:
     */
    Order secKill(User user, GoodsVO goods);

    /**
     * @Description:订单详情
     * @Param:
     * @Return:
     */
    OrderDetailVO detail(Long orderId);
}
