package com.ywzai.mcp.weixin.domain.adapter;

import com.ywzai.mcp.weixin.domain.model.WeiXinNoticeFunctionRequest;
import com.ywzai.mcp.weixin.domain.model.WeiXinNoticeFunctionResponse;

import java.io.IOException;

public interface IWeiXinPort {
    WeiXinNoticeFunctionResponse weixinNotice(WeiXinNoticeFunctionRequest request) throws IOException;

}
