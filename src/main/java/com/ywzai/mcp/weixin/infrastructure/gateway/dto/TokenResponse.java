package com.ywzai.mcp.weixin.infrastructure.gateway.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ywz
 * @CreateTime: 2025-09-15
 * @Description: 获取微信公众平台token的请求返回体
 * @Version: 1.0
 */
@Getter
@Setter
public class TokenResponse {

    private String access_token;
    private int expires_in;
    private String errcode;
    private String errmsg;
}
