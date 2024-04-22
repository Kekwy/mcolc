package com.kekwy.mcolc.controller;


import com.kekwy.mcolc.controller.api.AuthAPI;
import com.kekwy.mcolc.util.ResponseBuilder;
import com.kekwy.mcolc.vo.RedirectUrl;
import com.kekwy.mcolc.vo.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthAPI {

    @Override
    public ResponseEntity<ResponseBody> redirectToNmoSkinAuthPage() {
        ResponseBody body = new ResponseBuilder().payload(new RedirectUrl("https://www.baidu.com")).build();
        return ResponseEntity.ok(body);
    }

    @Override
    public String redirectToMicrosoftAuthPage() {
        return null;
    }
}
