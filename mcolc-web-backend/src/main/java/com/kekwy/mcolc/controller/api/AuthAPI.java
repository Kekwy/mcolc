package com.kekwy.mcolc.controller.api;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/auth")
public interface AuthAPI {
    @GetMapping("/nmo_skin")
    void redirectToNmoSkinAuthPage(HttpServletResponse response) throws IOException;

    @GetMapping("/microsoft")
    String  redirectToMicrosoftAuthPage();

}