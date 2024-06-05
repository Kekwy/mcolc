package com.kekwy.mcolc.controller.api;

import com.kekwy.mcolc.model.vo.ResponseBody;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/player")
public interface PlayerAPI {
    @GetMapping("/details")
    ResponseEntity<ResponseBody> details();

    @GetMapping("/avatar")
    ResponseEntity<Resource> avatar();

}
