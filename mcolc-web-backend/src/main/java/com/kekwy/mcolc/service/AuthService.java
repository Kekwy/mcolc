package com.kekwy.mcolc.service;


import org.jetbrains.annotations.NotNull;

public interface AuthService {

    String getAuthUrlWithParam(String authName);

    String getAuthToken(String authName, String code);

    @NotNull String getAuthUrl();

    @NotNull String getAuthToken(String code);
}
