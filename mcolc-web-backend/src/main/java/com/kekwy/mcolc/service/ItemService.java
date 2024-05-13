package com.kekwy.mcolc.service;

import org.springframework.core.io.Resource;

import java.util.Map;

public interface ItemService {

    Map<String, String> getInventory(String name, String id);

    Resource getIcon(String name);
}
