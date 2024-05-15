package com.kekwy.mcolc.service;

import com.kekwy.mcolc.model.PlayerDetails;

public interface InventoryService {
    void saveOrUpdate(String uuid, PlayerDetails.Inventory inventory);

}
