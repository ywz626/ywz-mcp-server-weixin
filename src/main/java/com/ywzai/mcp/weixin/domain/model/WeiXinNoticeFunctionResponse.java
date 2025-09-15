package com.ywzai.mcp.weixin.domain.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

/**
 * @Author: ywz
 * @CreateTime: 2025-09-15
 * @Description: ai模型请求返回体
 * @Version: 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeiXinNoticeFunctionResponse {

    @JsonProperty(required = true,value = "success")
    @JsonPropertyDescription("success")
    private boolean success;
}
