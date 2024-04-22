package com.kekwy.mcolc.service.impl;

import com.kekwy.mcolc.model.GameRoleDetails;
import com.kekwy.mcolc.service.GameRoleService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("microsoftGameRoleService")
public class MicrosoftGameRoleService implements GameRoleService {
    @Override
    public List<GameRoleDetails> getGameRoleDetailsList(String authToken) {
        return List.of(new GameRoleDetails("null", "null"), new GameRoleDetails("null", "null"));
    }
}
