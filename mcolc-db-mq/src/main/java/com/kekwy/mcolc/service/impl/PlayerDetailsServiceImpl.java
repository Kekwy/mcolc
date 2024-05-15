package com.kekwy.mcolc.service.impl;

import com.kekwy.mcolc.model.PlayerDetails;
import com.kekwy.mcolc.repo.PlayerDetailsRepository;
import com.kekwy.mcolc.service.PlayerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerDetailsServiceImpl implements PlayerDetailsService {

    private PlayerDetailsRepository playerDetailsRepository;

    @Autowired
    public void setPlayerDetailsRepository(PlayerDetailsRepository playerDetailsRepository) {
        this.playerDetailsRepository = playerDetailsRepository;
    }

    @Override
    public void saveOrUpdate(PlayerDetails playerDetails) {
        playerDetailsRepository.save(playerDetails);
    }
}
