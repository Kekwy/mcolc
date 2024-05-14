package com.kekwy.mcolc.util;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

public class HttpRequestUtil {

    public static <T> ResponseEntity<T> get(String url, @Nullable HttpEntity<?> requestEntity,
                                            ParameterizedTypeReference<T> responseType, Object... uriVariables) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType, uriVariables);
    }

    public static <T> ResponseEntity<T> get(String url, @Nullable HttpEntity<?> requestEntity,
                                            Class<T> responseType, Object... uriVariables) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType, uriVariables);
    }

}
