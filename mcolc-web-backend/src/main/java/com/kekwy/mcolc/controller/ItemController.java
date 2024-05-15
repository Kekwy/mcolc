package com.kekwy.mcolc.controller;

import com.kekwy.mcolc.controller.api.ItemAPI;
import com.kekwy.mcolc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class ItemController implements ItemAPI {

    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public ResponseEntity<Resource> icon(String key) {
        Resource resource = itemService.getIcon(key);
        if (resource != null) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
                    .contentType(MediaType.IMAGE_PNG).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
