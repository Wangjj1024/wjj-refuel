package com.wjj.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjj.miaosha.mapper.GoodsMapper;
import com.wjj.miaosha.pojo.Goods;
import com.wjj.miaosha.service.IGoodsService;
import com.wjj.miaosha.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjj
 * @since 2023-10-21
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    /**
     * @Description:获取商品列表
     * @Param:
     * @Return:
     */
    @Override
    public List<GoodsVO> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }

    /**
     * @Description:获取商品详情
     * @Param:
     * @Return:
     */
    @Override
    public GoodsVO findGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.findGoodsVoByGoodsId(goodsId);
    }
}
