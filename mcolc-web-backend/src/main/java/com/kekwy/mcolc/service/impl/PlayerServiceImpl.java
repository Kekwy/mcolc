package com.kekwy.mcolc.service.impl;

import com.kekwy.mcolc.config.McolcConfig;
import com.kekwy.mcolc.model.MCItem;
import com.kekwy.mcolc.model.PlayerDetails;
import com.kekwy.mcolc.repo.PlayerDetailsRepository;
import com.kekwy.mcolc.service.PlayerService;
import com.kekwy.mcolc.util.HttpRequestUtil;
import com.kekwy.mcolc.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private McolcConfig mcolcConfig;

    @Autowired
    public void setMcolcConfig(McolcConfig mcolcConfig) {
        this.mcolcConfig = mcolcConfig;
    }

    private PlayerDetailsRepository playerDetailsRepository;

    @Autowired
    public void setPlayerDetailsRepository(PlayerDetailsRepository playerDetailsRepository) {
        this.playerDetailsRepository = playerDetailsRepository;
    }

    @Override
    public PlayerDetails getPlayerDetails(String name, String id) {

        String uuid = UuidUtil.format(id);
        String url = mcolcConfig.getHttpUrl(uuid, name);

        ResponseEntity<PlayerDetails> response = HttpRequestUtil.get(url, HttpEntity.EMPTY, PlayerDetails.class);

        if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
            PlayerDetails playerDetails = response.getBody();
            //noinspection DataFlowIssue
            localizeItemName(playerDetails.getInventory().getArmor());
            localizeItemName(playerDetails.getInventory().getMain());
            localizeItemName(playerDetails.getInventory().getOffHand());
            localizeItemName(playerDetails.getInventory().getHotBar());
            return playerDetails;
        } else {
            Optional<PlayerDetails> playerDetailsOptional = playerDetailsRepository.findById(uuid);
            if (playerDetailsOptional.isPresent()) {
                PlayerDetails playerDetails = playerDetailsOptional.get();
                if (playerDetails.getName().equals(name)) {
                    return playerDetails;
                }
            }
        }
        return null;
    }

    private void localizeItemName(List<MCItem> items) {
        for (MCItem item : items) {
            if (item == null) continue;
            String localizedName = mcolcConfig.getLocalizedName(item.getKey());
            if (localizedName != null) {
                item.setName(localizedName);
            }
        }
    }

}
