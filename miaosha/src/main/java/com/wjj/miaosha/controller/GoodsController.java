package com.wjj.miaosha.controller;

import com.wjj.miaosha.pojo.User;
import com.wjj.miaosha.service.IGoodsService;
import com.wjj.miaosha.service.IUserService;
import com.wjj.miaosha.vo.DetailVO;
import com.wjj.miaosha.vo.GoodsVO;
import com.wjj.miaosha.vo.RespBean;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 跳转商品列表页
 * 3万线程数
 * window优化前QPS：1332
 * 缓存QPS：3500
 * @Author: wjj
 * @CreateTime: 2023-10-18
 * @Version: 1.0
 */
@Controller
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    /**
     * @Description:跳转商品列表
     * @Param:
     * @Return:
     */
    @RequestMapping(value = "/toList", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toList(Model model, User user,
                         HttpServletRequest request, HttpServletResponse response) {
        //redis中获取页面，如果不为空，直接返回页面
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodsList");
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsService.findGoodsVo());
//        return "goodsList";
        WebContext context = new WebContext(request, response, request.getServletContext()
                , request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsList", context);
        if (!StringUtils.isEmpty(html)) {
            valueOperations.set("goodsList", html, 60, TimeUnit.SECONDS);
        }
        return html;
    }

    /**
     * @Description:跳转商品详情页面
     * @Param:
     * @Return:
     */
    @RequestMapping(value = "/toDetail2/{goodsId}", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toDetail2(Model model, User user, @PathVariable("goodsId") Long goodsId,
                            HttpServletRequest request, HttpServletResponse response) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //redis中获取页面不为空，直接返回页面
        String html = (String) valueOperations.get("goodsDetail:" + goodsId);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        model.addAttribute("user", user);
        GoodsVO goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
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
        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsDetail", context);
        if (!StringUtils.isEmpty(html)) {
            valueOperations.set("goodsDetail:" + goodsId, html, 60, TimeUnit.SECONDS);
        }
        return html;
//        return "goodsDetail";
    }


    /**
     * @Description:跳转商品详情页面
     * @Param:
     * @Return:
     */
    @GetMapping("/detail/{goodsId}")
    @ResponseBody
    public RespBean toDetail(Model model, User user, @PathVariable("goodsId") Long goodsId) {
        GoodsVO goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
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
        DetailVO detailVO = new DetailVO();
        detailVO.setUser(user);
        detailVO.setGoodsVO(goodsVo);
        detailVO.setSeckillStatus(remainSeconds);
        detailVO.setRemainSeconds(secKillStatus);
        return RespBean.success(detailVO);
    }
}
