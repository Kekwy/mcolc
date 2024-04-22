package com.kekwy.mcolc.service.impl;

import com.kekwy.mcolc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    public void setClientRegistrationRepository(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    public String getAuthUrlWithParam(String authName) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(authName);
        if (clientRegistration != null) {
            String authorizationUri = clientRegistration.getProviderDetails().getAuthorizationUri();
            String redirectUri = clientRegistration.getRedirectUri();
            String clientId = clientRegistration.getClientId();
            // 构造授权请求 URL
            return UriComponentsBuilder.fromUriString(authorizationUri)
                    .queryParam("client_id", clientId)
                    .queryParam("redirect_uri", redirectUri)
                    .queryParam("response_type", "code")
                    .build().toUriString();
        }
        return null;
    }

    @Override
    public String getAuthToken(String authName, String code) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(authName);
        if (clientRegistration != null) {
            String tokenUri = clientRegistration.getProviderDetails().getTokenUri();
            String grantType = clientRegistration.getAuthorizationGrantType().getValue();
            String redirectUri = clientRegistration.getRedirectUri();
            String clientId = clientRegistration.getClientId();
            String clientSecret = clientRegistration.getClientSecret();

            MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("grant_type", grantType);
            requestBody.add("client_id", clientId);
            requestBody.add("client_secret", clientSecret);
            requestBody.add("redirect_uri", redirectUri);
            requestBody.add("code", code);

            HttpEntity<MultiValueMap<String, Object>> fromEntity = new HttpEntity<>(requestBody);
            ParameterizedTypeReference<Map<String, Object>> typeReference = new ParameterizedTypeReference<>() {
            };

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map<String, Object>> response =
                    restTemplate.exchange(tokenUri, HttpMethod.POST, fromEntity, typeReference);
            if (response.getBody() != null) {
                return response.getBody().get("access_token").toString();
            }
        }
        return null;
    }
}
