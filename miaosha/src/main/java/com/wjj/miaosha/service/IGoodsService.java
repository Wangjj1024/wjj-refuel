package com.wjj.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjj.miaosha.pojo.Goods;
import com.wjj.miaosha.vo.GoodsVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjj
 * @since 2023-10-21
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * @Description:获取商品列表
     * @Param:
     * @Return:
     */
    List<GoodsVO> findGoodsVo();

    /**
     * @Description:获取商品信息
     * @Param:
     * @Return:
     */
    GoodsVO findGoodsVoByGoodsId(Long goodsId);
}
