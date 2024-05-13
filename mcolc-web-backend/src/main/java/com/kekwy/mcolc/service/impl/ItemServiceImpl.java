package com.kekwy.mcolc.service.impl;

import com.kekwy.mcolc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    private ResourceLoader resourceLoader;

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Map<String, String> getInventory(String name, String id) {
        // 访问模组中的web服务器，从中获取角色数据
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.
        return Map.of(
                "username", name,
                "item1", "23324",
                "item2", "234325456");
    }

    @Override
    public Resource getIcon(String name) {
        Resource resource = resourceLoader.getResource("classpath:/static/icons/" + name + ".png");
        if (resource.exists()) return resource;
        else return null;
    }
}
