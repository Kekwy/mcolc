package com.kekwy.mcolc.service;

import com.kekwy.mcolc.model.PlayerDetails;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;

public interface PlayerService {

    PlayerDetails getPlayerDetails(String name, String id);

    Resource getPlayerAvatar(String uuid);
}
