package com.wjj.miaosha.vo;

import com.wjj.miaosha.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 详情返回对象
 * @Author: wjj
 * @CreateTime: 2023-11-08
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailVO {

    private User user;

    private GoodsVO goodsVO;

    private int seckillStatus;

    private int remainSeconds;
}
