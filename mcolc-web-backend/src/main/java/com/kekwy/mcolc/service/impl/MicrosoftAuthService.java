package com.kekwy.mcolc.service.impl;

import com.kekwy.mcolc.model.vo.TokenUhs;
import com.kekwy.mcolc.model.vo.XboxLiveAuthRequest;
import com.kekwy.mcolc.model.vo.XboxLiveTokenResponse;
import com.kekwy.mcolc.service.AuthService;
import com.kekwy.mcolc.util.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("DataFlowIssue")
@Service
public class MicrosoftAuthService implements AuthService {
    // TODO: 2024/5/1 静态分析，优化代码
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
            Set<String> scopes = clientRegistration.getScopes();
            // 构造授权请求 URL
            return UriComponentsBuilder.fromUriString(authorizationUri)
                    .queryParam("client_id", clientId)
                    .queryParam("redirect_uri", redirectUri)
                    .queryParam("response_type", "code")
                    .queryParam("scope", scopes)
                    .build().toUriString();
        }
        return null;
    }

    private static final ParameterizedTypeReference<Map<String, Object>> RESPONSE_TYPE = new ParameterizedTypeReference<>() {
    };

    @Override
    public String getAuthToken(String authName, String code) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(authName);
        if (clientRegistration != null) {
            String tokenUri = clientRegistration.getProviderDetails().getTokenUri();
            String grantType = clientRegistration.getAuthorizationGrantType().getValue();
            String redirectUri = clientRegistration.getRedirectUri();
            String clientId = clientRegistration.getClientId();
//            String clientSecret = clientRegistration.getClientSecret();

            MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("grant_type", grantType);
            requestBody.add("client_id", clientId);
//            requestBody.add("client_secret", clientSecret);
            requestBody.add("redirect_uri", redirectUri);
            requestBody.add("code", code);

            HttpEntity<MultiValueMap<String, Object>> fromEntity = new HttpEntity<>(requestBody);


            RestTemplate restTemplate = new RestTemplate();
//            restTemplate.setInterceptors(Collections.singletonList(new HttpLoggingInterceptor()));

            ResponseEntity<Map<String, Object>> response =
                    restTemplate.exchange(tokenUri, HttpMethod.POST, fromEntity, RESPONSE_TYPE);
            if (response.getBody() != null) {
                return response.getBody().get("access_token").toString();
            }
        }
        return null;
    }

    @Override
    public @NotNull String getAuthUrl() {
        return getAuthUrlWithParam("microsoft");
    }


    private TokenUhs getXboxLiveToken(String msaToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        XboxLiveAuthRequest body = new XboxLiveAuthRequest();
        body.properties().authMethod("RPS");
        body.properties().siteName("user.auth.xboxlive.com");
        body.properties().rpsTicket("d=" + msaToken);
        body.relyingParty("http://auth.xboxlive.com");
        body.tokenType("JWT");
        HttpEntity<XboxLiveAuthRequest> httpEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(Collections.singletonList(new HttpLoggingInterceptor()));
        ResponseEntity<XboxLiveTokenResponse> response =
                restTemplate.exchange("https://user.auth.xboxlive.com/user/authenticate",
                        HttpMethod.POST, httpEntity, XboxLiveTokenResponse.class);
        if (response.getBody() != null) {
            return new TokenUhs(
                    response.getBody().getToken(),
                    response.getBody().getDisplayClaims().getXui().get(0).getUhs());
        }
        return null;
    }

    private String getXSTSToken(String xboxLiveToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String body = """
                {
                    "Properties": {
                        "SandboxId": "RETAIL",
                        "UserTokens": [
                            "%s"
                        ]
                    },
                    "RelyingParty": "rp://api.minecraftservices.com/",
                    "TokenType": "JWT"
                }
                """.formatted(xboxLiveToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(Collections.singletonList(new HttpLoggingInterceptor()));
        ResponseEntity<Map<String, Object>> response =
                restTemplate.exchange("https://xsts.auth.xboxlive.com/xsts/authorize",
                        HttpMethod.POST, httpEntity, RESPONSE_TYPE);
        if (response.getBody() != null) {
            return response.getBody().get("Token").toString();
        }
        return null;
    }

    private String getMinecraftToken(String xstsToken, String uhs) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = """
                {
                   "identityToken" : "XBL3.0 x=%s;%s",
                   "ensureLegacyEnabled" : true
                }
                """.formatted(uhs, xstsToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(Collections.singletonList(new HttpLoggingInterceptor()));
        ResponseEntity<Map<String, Object>> response =
                restTemplate.exchange("https://api.minecraftservices.com/authentication/login_with_xbox",
                        HttpMethod.POST, httpEntity, RESPONSE_TYPE);
        if (response.getBody() != null) {
            return response.getBody().get("access_token").toString();
        }
        return null;
    }

    @Override
    public @NotNull String getAuthToken(String code) {
        String msaToken = getAuthToken("microsoft", code);
        TokenUhs xboxLiveTokenUhs = getXboxLiveToken(msaToken);
        String xstsToken = getXSTSToken(xboxLiveTokenUhs.getToken());
        return "Bearer " + getMinecraftToken(xstsToken, xboxLiveTokenUhs.getUhs());
    }

}
