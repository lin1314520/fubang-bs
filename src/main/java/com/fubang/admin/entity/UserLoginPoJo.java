package com.fubang.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder(buildMethodName = "newInstance")
@AllArgsConstructor
public class UserLoginPoJo implements Serializable {

    private static final long serialVersionUID = -1004783426502579042L;
    // 1验证码登录 2密码登录 3微信授权登录　５微信小程序授权登录
    private Integer type;

    private String phone;
    private String code;
    private String password;

    //微信授权登录
    private String accessToken;

   // private String openid;

    public UserLoginPoJo() {
    }
}
