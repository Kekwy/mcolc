package com.kekwy.mcolc.service.impl;

import com.kekwy.mcolc.mapper.PlayerDetailsMapper;
import com.kekwy.mcolc.model.PlayerDetails;
import com.kekwy.mcolc.service.InventoryService;
import com.kekwy.mcolc.service.PlayerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerDetailsServiceImpl implements PlayerDetailsService {

    private PlayerDetailsMapper playerDetailsMapper;

    private InventoryService inventoryService;

    @Autowired
    public void setPlayerDetailsMapper(PlayerDetailsMapper playerDetailsMapper) {
        this.playerDetailsMapper = playerDetailsMapper;
    }

    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Override
    @Transactional
    public void saveOrUpdate(PlayerDetails playerDetails) {
        if (playerDetailsMapper.exists(playerDetails.getUuid())) {
            playerDetailsMapper.insert(playerDetails);
        } else {
            playerDetailsMapper.update(playerDetails);
        }
        inventoryService.saveOrUpdate(playerDetails.getUuid(), playerDetails.getInventory());
    }
}
