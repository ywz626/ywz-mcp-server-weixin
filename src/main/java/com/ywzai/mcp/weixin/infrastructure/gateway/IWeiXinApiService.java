package com.ywzai.mcp.weixin.infrastructure.gateway;


import com.ywzai.mcp.weixin.infrastructure.gateway.dto.TemplateMessageRequest;
import com.ywzai.mcp.weixin.infrastructure.gateway.dto.TokenRequest;
import com.ywzai.mcp.weixin.infrastructure.gateway.dto.TokenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @Author: ywz
 * @CreateTime: 2025-09-15
 * @Description: 微信Api调用
 * @Version: 1.0
 */
public interface IWeiXinApiService {

    @GET("cgi-bin/token")
    Call<TokenResponse> getToken(@Query("grant_type") String grantType,
                                 @Query("appid") String appId,
                                 @Query("secret") String appSecret);


    @POST("cgi-bin/message/template/send")
    Call<Void> sendTemplateMessage(@Query("access_token") String accessToken, @Body TemplateMessageRequest templateMessageRequest);
}
