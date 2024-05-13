package com.kekwy.mcolc.service.impl;

import com.kekwy.mcolc.model.GameRoleDetails;
import com.kekwy.mcolc.service.GameRoleService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service("microsoftGameRoleService")
public class MicrosoftGameRoleService implements GameRoleService {
    @Override
    public GameRoleDetails getGameRoleDetailsList(String authToken) {
        return new GameRoleDetails("null", "null");
    }

    @Override
    public GameRoleDetails getGameRoleDetails(String accessToken) {
        // 缓存 redis
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<Map<String, Object>> typeReference = new ParameterizedTypeReference<>() {
        };
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, Object>> response =
                restTemplate.exchange("https://api.minecraftservices.com/minecraft/profile", HttpMethod.GET, httpEntity, typeReference);
        if (response.getBody() != null) {
            return new GameRoleDetails(
                    response.getBody().get("name").toString(),
                    response.getBody().get("id").toString()
            );
        }
        return null;
    }
}
