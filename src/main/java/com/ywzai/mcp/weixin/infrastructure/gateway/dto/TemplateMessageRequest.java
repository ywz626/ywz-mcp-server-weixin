package com.ywzai.mcp.weixin.infrastructure.gateway.dto;


import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ywz
 * @CreateTime: 2025-09-15
 * @Description: 发送模板请求的请求体
 * @Version: 1.0
 */
public class TemplateMessageRequest {
    private String touser = "oM3ts6itlenZwsCGlzEy4ddJXIQ0";
    private String template_id = "xoeJxsdqhcHc3qLa-yG4JoWcOFcFHc9-3zBefm89_8k";
    private String url = "https://weixin.qq.com";
    private Map<String, Map<String,String>> data = new HashMap<>();

    public TemplateMessageRequest(String touser, String template_id) {
        this.touser = touser;
        this.template_id = template_id;
    }

    public void put(TemplateKey key, String value) {
        data.put(key.getCode(), new HashMap<>() {
            @Serial
            private static final long serialVersionUID = 7092338402387318563L;

            {
                put("value", value);
            }
        });
    }

    public static void put(Map<String, Map<String, String>> data, TemplateKey key, String value) {
        data.put(key.getCode(), new HashMap<>() {
            @Serial
            private static final long serialVersionUID = 7092338402387318563L;

            {
                put("value", value);
            }
        });
    }


    public enum TemplateKey {
        platform("platform_name","平台"),
        subject("subject_name","主题"),
        description("description_name","简述"),
        ;

        private String code;
        private String desc;

        TemplateKey(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }
}
