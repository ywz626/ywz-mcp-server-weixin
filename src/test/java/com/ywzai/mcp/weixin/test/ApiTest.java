package com.ywzai.mcp.weixin.test;


import com.alibaba.fastjson2.JSON;
import com.ywzai.mcp.weixin.infrastructure.gateway.IWeiXinApiService;
import com.ywzai.mcp.weixin.infrastructure.gateway.dto.TemplateMessageRequest;
import com.ywzai.mcp.weixin.infrastructure.gateway.dto.TokenRequest;
import com.ywzai.mcp.weixin.infrastructure.gateway.dto.TokenResponse;
import com.ywzai.mcp.weixin.types.properties.WeiXinApiProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private WeiXinApiProperties weiXinApiProperties;
    @Resource
    private IWeiXinApiService weixinApiService;

    private String accessToken = "96_0aZlO_q8uX34KkVWypylCirKH9yJ1HUSVM9e95oHCS9-JyiWoIaxnk-bfcnFGJ_GJBlJ8yq7FqGBzBtrULTs4pOgUMvQTuQZNG41w8vSQUiX6I5t_xL9rjTNhs4KBMgAIASJU";

    @Before
    public void before() throws IOException {
        TokenResponse call = weixinApiService.getToken("client_credential", weiXinApiProperties.getAppid(), weiXinApiProperties.getAppsecret()).execute().body();
        accessToken = call.getAccess_token();
        log.info("weixin accessToken:{}", accessToken);
    }

    @Test
    public void test_template_message() throws IOException {
        Map<String, Map<String, String>> data = new HashMap<>();
        TemplateMessageRequest.put(data, TemplateMessageRequest.TemplateKey.platform, "CSDN");
        TemplateMessageRequest.put(data, TemplateMessageRequest.TemplateKey.subject, "Java求职面试：从Spring Boot到微服务的技术探索");
        TemplateMessageRequest.put(data, TemplateMessageRequest.TemplateKey.description, "面试官是一位经验丰富且严肃的技术大牛，而谢飞机则是以幽默著称的“水货”程序员。：Elasticsearch用于存储，Logstash用于日志收集和传输。");

        TemplateMessageRequest templateMessageDTO = new TemplateMessageRequest(weiXinApiProperties.getTouser(), weiXinApiProperties.getTemplate_id());
        templateMessageDTO.setUrl("https://blog.csdn.net/2504_93398205/article/details/151725040?spm=1011.2415.3001.5331");
        templateMessageDTO.setData(data);

        Call<Void> call = weixinApiService.sendTemplateMessage(accessToken, templateMessageDTO);
        call.execute();

        log.info("请求参数:{}", JSON.toJSONString(templateMessageDTO));
    }

}
