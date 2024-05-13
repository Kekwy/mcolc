package com.kekwy.mcolc.service.impl;

import com.kekwy.mcolc.model.GameRoleDetails;
import com.kekwy.mcolc.service.GameRoleService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component("nmo-skinGameRoleService")
public class NmoSkinGameRoleService implements GameRoleService {

    @Override
    public GameRoleDetails getGameRoleDetailsList(String authToken) {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.exchange()
        return new GameRoleDetails("null", "null");
    }

    @Override
    public GameRoleDetails getGameRoleDetails(String authToken) {
        return null;
    }
}
