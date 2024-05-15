package com.kekwy.mcolc.service.impl;

import com.kekwy.mcolc.mapper.InventoryMapper;
import com.kekwy.mcolc.model.MCItem;
import com.kekwy.mcolc.model.PlayerDetails;
import com.kekwy.mcolc.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryMapper inventoryMapper;

    @Autowired
    public void setInventoryMapper(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    @Transactional
    public void saveOrUpdate(String uuid, PlayerDetails.Inventory inventory) {
        saveOrUpdateSlot(uuid, "main", inventory.getMain());
        saveOrUpdateSlot(uuid, "armor", inventory.getArmor());
        saveOrUpdateSlot(uuid, "hotBar", inventory.getHotBar());
        saveOrUpdateSlot(uuid, "offHand", inventory.getOffHand());
    }

    private void saveOrUpdateSlot(String uuid, String slot, List<MCItem> itemList) {
        int i = 0;
        for (MCItem item : itemList) {
            String itemUuid = inventoryMapper.selectItemUuid(uuid, slot, i);

            if (itemUuid == null) {

            }

            i++;
        }
    }

}
