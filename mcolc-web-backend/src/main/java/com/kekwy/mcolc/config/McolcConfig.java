package com.kekwy.mcolc.config;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "mcolc")
public class McolcConfig implements InitializingBean {

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @SuppressWarnings("HttpUrlsUsage")
    public String getHttpUrl(String uuid, String name) {
        return "http://" + ip + ":" + port + "/player?uuid=" + uuid + "&name=" + name;
    }

    private String ip = "localhost";

    private Integer port = 27272;

    private Lang language = Lang.EN_US;

    public Lang getLanguage() {
        return language;
    }

    public void setLanguage(Lang language) {
        this.language = language;
    }

    private ResourceLoader resourceLoader;

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private Map<String, String> dictionary;

    public String getLocalizedName(String key) {
        return dictionary.get(key);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (language == Lang.EN_US) {
            dictionary = new HashMap<>();
            return;
        }
        Resource langFile = resourceLoader.getResource(
                "classpath:/static/lang/" + language.name().toLowerCase() + ".json");
        ObjectMapper objectMapper = new ObjectMapper();
        dictionary = objectMapper.readValue(langFile.getInputStream(), new TypeReference<>() {
        });


    }

    public enum Lang {
        EN_US,
        ZH_CN
    }

}
