package com.wjj.miaosha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjj.miaosha.pojo.Goods;
import com.wjj.miaosha.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wjj
 * @since 2023-10-21
 */
public interface GoodsMapper extends BaseMapper<Goods> {


    /**
     * @Description:获取商品列表
     * @Param:
     * @Return:
     */
    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
