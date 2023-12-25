package com.wjj.miaosha.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjj.miaosha.pojo.Order;
import com.wjj.miaosha.pojo.SeckillOrder;
import com.wjj.miaosha.pojo.User;
import com.wjj.miaosha.service.IGoodsService;
import com.wjj.miaosha.service.ISeckillOrderService;
import com.wjj.miaosha.service.impl.OrderServiceImpl;
import com.wjj.miaosha.vo.GoodsVO;
import com.wjj.miaosha.vo.RespBean;
import com.wjj.miaosha.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 秒杀
 * @Author: wjj
 * @CreateTime: 2023-10-23
 * @Version: 1.0
 */
@Controller
@RequestMapping("/seckill")
public class SecKillController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private OrderServiceImpl orderService;

    /**
     * @Description:秒杀 windows优化前QPS：1278
     * @Param:
     * @Return:
     */
    @RequestMapping(value = "/doSeckill", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSecKill(User user, String goodsId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERRRO);
        }
//        System.out.println(goodsId);
        String s1 = goodsId.split("=")[1];
        Long goodsID = Long.parseLong(s1);
        GoodsVO goods = goodsService.findGoodsVoByGoodsId(goodsID);
        if (goods.getStockCount() < 1) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId()).eq("goods_id", goodsID));
        if (seckillOrder != null) {
            return RespBean.error(RespBeanEnum.PEMPTY_ERROR);
        }
        Order order = orderService.secKill(user, goods);
        return RespBean.success(order);
    }

    /**
     * @Description:秒杀 windows优化前QPS：1278
     * @Param:
     * @Return:
     */
    @RequestMapping(value = "/doSeckill2", method = RequestMethod.POST)
    public String doSecKill2(Model model, User user, Long goodsId) {
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        GoodsVO goods = goodsService.findGoodsVoByGoodsId(goodsId);
        if (goods.getStockCount() < 1) {
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "secKillFail";
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId()).eq("goods_id", goodsId));
        if (seckillOrder != null) {
            model.addAttribute("erromsg", RespBeanEnum.PEMPTY_ERROR.getMessage());
            return "secKillFail";
        }
        Order order = orderService.secKill(user, goods);
        model.addAttribute("order", order);
        model.addAttribute("goods", goods);
        return "orderDetail";
    }
}
