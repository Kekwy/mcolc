package com.kekwy.mcolc.controller;


import com.kekwy.mcolc.controller.api.AuthAPI;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthController implements AuthAPI {

    @Override
    public void redirectToNmoSkinAuthPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://baidu.com");
    }

    @Override
    public String redirectToMicrosoftAuthPage() {
        return null;
    }
}
