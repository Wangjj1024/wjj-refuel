package com.wjj.miaosha.utils;

import org.thymeleaf.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: wjj
 * @CreateTime: 2023-10-17
 * @Description: 手机号码校验
 * @Version: 1.0
 */
public class ValidatorUtil {

    private static final Pattern mobile_patter = Pattern.compile("[1]([3-9])[0-9]{9}$");

    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)){
            return false;
        }
        Matcher matcher = mobile_patter.matcher(mobile);
        return matcher.matches();
    }
}
