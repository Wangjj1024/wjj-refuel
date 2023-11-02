package com.wjj.miaosha.vo;

import com.wjj.miaosha.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Author: wjj
 * @CreateTime: 2023-10-16
 * @Description: 登录参数
 * @Version: 1.0
 */
@Data
public class LoginVO {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
