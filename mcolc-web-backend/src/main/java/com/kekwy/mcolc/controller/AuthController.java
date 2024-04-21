package com.kekwy.mcolc.controller;


import com.kekwy.mcolc.controller.api.AuthAPI;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthAPI {

    @Override
    public String  redirectToNmoSkinAuthPage() {
        return "redirect://www.baidu.com";
    }

    @Override
    public String redirectToMicrosoftAuthPage() {
        return null;
    }
}
