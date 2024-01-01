package com.wjj.miaosha.vo;

import com.wjj.miaosha.pojo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 订单详情返回对象
 * @Author: wjj
 * @CreateTime: 2024-01-01
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailVO {
    private Order order;

    private GoodsVO goodsVO;
}
