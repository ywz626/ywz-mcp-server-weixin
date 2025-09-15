package com.ywzai.mcp.weixin.domain.service;


import com.ywzai.mcp.weixin.domain.adapter.IWeiXinPort;
import com.ywzai.mcp.weixin.domain.model.WeiXinNoticeFunctionRequest;
import com.ywzai.mcp.weixin.domain.model.WeiXinNoticeFunctionResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author: ywz
 * @CreateTime: 2025-09-15
 * @Description: ai的方法类
 * @Version: 1.0
 */
@Slf4j
@Service
public class WeiXinNoticeService {

    @Resource
    private IWeiXinPort weiXInPort;

    @Tool(description = "微信公众号消息通知")
    public WeiXinNoticeFunctionResponse weixinNotice(WeiXinNoticeFunctionRequest request) throws IOException {
        log.info("微信消息通知，平台:{} 主题:{} 描述:{}", request.getPlatform(), request.getSubject(), request.getDescription());
        return weiXInPort.weixinNotice(request);
    }
}
