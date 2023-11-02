package com.wjj.miaosha.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 商品返回对象
 * @Author: wjj
 * @CreateTime: 2023-10-21
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo {

    private Long id;

    private String goodsName;

    private String goodsTitle;

    private String goodsImg;

    private String goodsDetail;

    private BigDecimal goodsPrice;

    private Integer goodsStock;

    private BigDecimal seckillPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;


}
