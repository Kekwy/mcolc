package com.kekwy.mcolc.controller.api;

import com.kekwy.mcolc.vo.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/auth")
public interface AuthAPI {
    @GetMapping("/nmo_skin")
    ResponseEntity<ResponseBody> redirectToNmoSkinAuthPage() throws IOException;

    @GetMapping("/microsoft")
    String  redirectToMicrosoftAuthPage();

}