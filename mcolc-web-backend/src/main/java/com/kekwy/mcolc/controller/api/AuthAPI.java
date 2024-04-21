package com.kekwy.mcolc.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthAPI {
    @GetMapping("/nmo_skin")
    String  redirectToNmoSkinAuthPage();

    @GetMapping("/microsoft")
    String  redirectToMicrosoftAuthPage();

}