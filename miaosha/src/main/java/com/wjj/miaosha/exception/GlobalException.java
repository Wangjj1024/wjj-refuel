package com.wjj.miaosha.exception;

import com.wjj.miaosha.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wjj
 * @CreateTime: 2023-10-18
 * @Description: 全局异常
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private RespBeanEnum respBeanEnum;
}
