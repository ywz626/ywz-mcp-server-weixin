package com.ywzai.mcp.weixin.infrastructure.adapter;


import com.google.common.cache.Cache;
import com.ywzai.mcp.weixin.domain.adapter.IWeiXinPort;
import com.ywzai.mcp.weixin.domain.model.WeiXinNoticeFunctionRequest;
import com.ywzai.mcp.weixin.domain.model.WeiXinNoticeFunctionResponse;
import com.ywzai.mcp.weixin.infrastructure.gateway.IWeiXinApiService;
import com.ywzai.mcp.weixin.infrastructure.gateway.dto.TemplateMessageRequest;
import com.ywzai.mcp.weixin.infrastructure.gateway.dto.TokenRequest;
import com.ywzai.mcp.weixin.infrastructure.gateway.dto.TokenResponse;
import com.ywzai.mcp.weixin.types.properties.WeiXinApiProperties;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ywz
 * @CreateTime: 2025-09-15
 * @Description: 微信ai调用接口实现
 * @Version: 1.0
 */
@Component
public class WeiXinPort implements IWeiXinPort {

    @Resource
    private WeiXinApiProperties weiXinApiProperties;

    @Resource
    private IWeiXinApiService weiXinApiService;

    @Resource
    private Cache<String, String> weixinAccessToken;

    @Override
    public WeiXinNoticeFunctionResponse weixinNotice(WeiXinNoticeFunctionRequest request) throws IOException {
        String accessToken = weixinAccessToken.getIfPresent(weiXinApiProperties.getAppid());
        if (null == accessToken) {
            TokenResponse response = weiXinApiService.getToken("client_credential", weiXinApiProperties.getAppid(), weiXinApiProperties.getAppsecret()).execute().body();
            assert response != null;
            accessToken = response.getAccess_token();
            weixinAccessToken.put(weiXinApiProperties.getAppid(), accessToken);
        }
        // 2. 发送模板消息
        Map<String, Map<String, String>> data = new HashMap<>();
        TemplateMessageRequest.put(data, TemplateMessageRequest.TemplateKey.platform, request.getPlatform());
        TemplateMessageRequest.put(data, TemplateMessageRequest.TemplateKey.subject, request.getSubject());
        TemplateMessageRequest.put(data, TemplateMessageRequest.TemplateKey.description, request.getDescription());

        TemplateMessageRequest templateMessageDTO = new TemplateMessageRequest(weiXinApiProperties.getTouser(), weiXinApiProperties.getTemplate_id());
        templateMessageDTO.setUrl(request.getJumpUrl());
        templateMessageDTO.setData(data);

        Call<Void> call = weiXinApiService.sendTemplateMessage(accessToken, templateMessageDTO);
        call.execute();

        WeiXinNoticeFunctionResponse weiXinNoticeFunctionResponse = new WeiXinNoticeFunctionResponse();
        weiXinNoticeFunctionResponse.setSuccess(true);

        return weiXinNoticeFunctionResponse;
    }
}
