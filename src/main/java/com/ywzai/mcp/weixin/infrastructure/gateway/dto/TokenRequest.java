package com.ywzai.mcp.weixin.infrastructure.gateway.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ywz
 * @CreateTime: 2025-09-15
 * @Description: 请求微信公众号token的DTO
 * @Version: 1.0
 */
@Getter
@Setter
public class TokenRequest {
    private final String grant_type = "client_credential";
    private String appid;
    private String secret;

    public TokenRequest(String secret, String appid) {
        this.secret = secret;
        this.appid = appid;
    }
}
