package com.kekwy.mcolc.service;

import com.kekwy.mcolc.model.GameRoleDetails;

import java.util.List;

public interface GameRoleService {

    List<GameRoleDetails> getGameRoleDetailsList(String authToken);
}
