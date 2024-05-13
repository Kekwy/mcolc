package com.kekwy.mcolc.service.impl;

import com.kekwy.mcolc.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public Map<String, String> getInventory(String name, String id) {
        return Map.of(
                "username", name,
                "item1", "23324",
                "item2", "234325456");
    }
}
