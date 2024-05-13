package com.kekwy.mcolc.controller.api;

import com.kekwy.mcolc.model.vo.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/auth")
public interface AuthAPI {

    @GetMapping("/token")
    ResponseEntity<ResponseBody> getAccessToken(@RequestParam("code") String code);

    @GetMapping()
    ResponseEntity<ResponseBody> redirectToAuthPage();

    @GetMapping("/{authName}")
    ResponseEntity<ResponseBody> redirectToAuthPage(@PathVariable("authName") String authName);

    @GetMapping("/{authName}/token")
    ResponseEntity<ResponseBody> getAuthToken(@PathVariable("authName") String authName, @RequestParam("code") String authCode);

}