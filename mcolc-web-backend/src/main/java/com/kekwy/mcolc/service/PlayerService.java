package com.kekwy.mcolc.service;

import com.kekwy.mcolc.model.PlayerDetails;
import org.springframework.core.io.Resource;

public interface PlayerService {

    PlayerDetails getPlayerDetails(String name, String id);

}
