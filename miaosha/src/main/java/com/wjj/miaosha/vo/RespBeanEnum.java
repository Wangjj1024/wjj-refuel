package com.wjj.miaosha.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author: wjj
 * @CreateTime: 2023-10-16
 * @Description: 公共返回对象枚举
 * @Version: 1.0
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    //通用
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常"),
    //登录模块5002xx
    LOGIN_ERROR(500210, "用户名或密码错误"),
    MOBILE_ERROR(500211, "手机号码格式不正确"),
    BIND_ERROR(500212, "参数校验异常"),
    MOBILE_NOT_EXITST(500213, "手机号码不存在"),
    PASSWORD_UPDATE_FAILED(500214, "密码更新失败"),
    SESSION_ERRRO(500215, "用户不存在"),
    //秒杀模块5005xx
    EMPTY_STOCK(500500, "库存不足"),
    PEMPTY_ERROR(500501, "该商品没人限购一件"),
    ;

    private final Integer code;

    private final String message;
}
