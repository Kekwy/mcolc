package com.kekwy.mcolc.controller.api;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/item")
public interface ItemAPI {

    @GetMapping("/{key}/icon")
    ResponseEntity<Resource> icon(@PathVariable("key") String key);

}
