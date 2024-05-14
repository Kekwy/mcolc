package com.kekwy.mcolc.service.impl;

import com.kekwy.mcolc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private ResourceLoader resourceLoader;

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Resource getIcon(String name) {
        Resource resource = resourceLoader.getResource("classpath:/static/icons/" + name + ".png");
        if (resource.exists()) return resource;
        else return null;
    }

}
