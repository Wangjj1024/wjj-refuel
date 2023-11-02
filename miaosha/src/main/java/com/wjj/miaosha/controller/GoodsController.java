package com.wjj.miaosha.controller;

import com.wjj.miaosha.pojo.User;
import com.wjj.miaosha.service.IGoodsService;
import com.wjj.miaosha.service.IUserService;
import com.wjj.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Description: 跳转商品列表页
 * 3万线程数
 * window优化前QPS：1332
 * Linux优化前QPS： 207
 *
 * @Author: wjj
 * @CreateTime: 2023-10-18
 * @Version: 1.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoodsService goodsService;

    /**
     * @Description:跳转商品列表
     * @Param:
     * @Return:
     */
    @RequestMapping("/toList")
    public String toList(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        return "goodsList";
    }

    /**
     * @Description:跳转商品详情页面
     * @Param:
     * @Return:
     */
    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, User user, @PathVariable("goodsId") Long goodsId) {
        model.addAttribute("user", user);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
//        秒杀状态
        int secKillStatus = 0;
//        秒杀倒计时
        int remainSeconds = 0;
//        秒杀未开始
        if (nowDate.before(startDate)) {
            remainSeconds = ((int) ((startDate.getTime() - nowDate.getTime()) / 1000));
        } else if (nowDate.after(endDate)) {
//            秒杀已结束
            secKillStatus = 2;
            remainSeconds = -1;
        } else {
//            秒杀中
            secKillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("secKillStatus", secKillStatus);
        model.addAttribute("goods", goodsVo);
        return "goodsDetail";
    }
}
