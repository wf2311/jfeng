package com.wf2311.repository.consts;

import com.wf2311.repository.annoation.EnableAccessEnum;

/**
 * 用户注册方式
 * @author wf2311
 */
@EnableAccessEnum(name = "用户注册方式")
public enum RegisterType {
    USER_NAME(1, "用户名"),
    MOBILE(2, "手机号码"),
    EMAIL(3, "电子邮箱"),
    WECHAT(4, "微信"),
    WEIBO(5, "微博"),
    ALIPAY(6, "支付宝"),
    BAIDU(7, "百度"),
    GOOGLE(8, "谷歌"),
    FACEBOOK(9, "facebook"),
    GITHUB(10, "github");

    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    RegisterType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
