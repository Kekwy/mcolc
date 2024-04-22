package com.kekwy.mcolc.service;

public interface AuthService {

    String getAuthUrlWithParam(String authName);

    String getAuthToken(String authName, String code);
}
