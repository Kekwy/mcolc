package com.kekwy.mcolc.controller;


import com.kekwy.mcolc.controller.api.AuthAPI;
import com.kekwy.mcolc.model.vo.RedirectUrl;
import com.kekwy.mcolc.service.AuthService;
import com.kekwy.mcolc.util.ResponseBuilder;
import com.kekwy.mcolc.model.vo.ResponseBody;
import com.kekwy.mcolc.model.vo.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthAPI {

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<ResponseBody> redirectToAuthPage(String authName) {
        String url = authService.getAuthUrlWithParam(authName);
        ResponseBuilder builder = new ResponseBuilder();
        if (url == null) {
            builder.code(404).message("认证服务名不存在");
        } else {
            builder.code(200).payload(new RedirectUrl(url)).build();
        }
        return ResponseEntity.ok(builder.build());
    }

    @Override
    public ResponseEntity<ResponseBody> getAuthToken(String authName, String code) {
        String token = authService.getAuthToken(authName, code);
        ResponseBuilder builder = new ResponseBuilder();
        if (token == null) {
            builder.code(404).message("认证服务名不存在");
        } else {
            builder.code(200).payload(new Token(token)).build();
        }
        return ResponseEntity.ok(builder.build());
    }

}
