package com.kekwy.mcolc.controller.api;

import com.kekwy.mcolc.model.vo.ResponseBody;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/player")
public interface PlayerAPI {
    @GetMapping("/details")
    ResponseEntity<ResponseBody> details();

    @GetMapping("/icon/{name}")
    ResponseEntity<Resource> icon(@PathVariable("name") String name);

}