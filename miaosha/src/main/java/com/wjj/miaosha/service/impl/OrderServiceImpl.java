package com.wjj.miaosha.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjj.miaosha.mapper.OrderMapper;
import com.wjj.miaosha.pojo.*;
import com.wjj.miaosha.service.IGoodsService;
import com.wjj.miaosha.service.IOrderService;
import com.wjj.miaosha.service.ISeckillGoodsService;
import com.wjj.miaosha.service.ISeckillOrderService;
import com.wjj.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wjj
 * @since 2023-10-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ISeckillGoodsService seckillGoodsService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private IGoodsService goodsService;

    /**
     * @Description:秒杀
     * @Param:
     * @Return:
     */
    @Override
    public Order secKill(User user, GoodsVo goods) {
        //更新秒杀订单==》秒杀商品表减库存
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<>(), "goods_id".equals(goods.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        seckillGoodsService.updateById(seckillGoods);
        //更新订单==》商品表减库存
        Goods goodsAll = goodsService.getOne(new QueryWrapper<>(), "goods_id".equals(goods.getId()));
        goodsAll.setGoodsStock(goodsAll.getGoodsStock() - 1);
        goodsService.updateById(goodsAll);

        //生成订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(goods.getId());
        seckillOrderService.save(seckillOrder);

        return order;
    }
}
