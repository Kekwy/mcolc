package com.kekwy.mcolc.controller.api;

import com.kekwy.mcolc.model.vo.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/item")
public interface ItemAPI {
    @GetMapping("/inventory")
    ResponseEntity<ResponseBody> inventory();
}
