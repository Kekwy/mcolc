package com.kekwy.mcolc.service;

import com.kekwy.mcolc.model.GameRoleDetails;

import java.util.List;

public interface GameRoleService {

    GameRoleDetails getGameRoleDetailsList(String authToken);

    GameRoleDetails getGameRoleDetails(String accessToken);
}
