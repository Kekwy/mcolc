package com.kekwy.mcolc.listener;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kekwy.mcolc.model.PlayerDetails;
import com.kekwy.mcolc.service.PlayerDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = "mcolc.db.queue", autoDelete = "false"),
                exchange = @Exchange(value = "mcolc.db.direct"),
                key = "mcolc.db.key"
        )
)
@Slf4j
public class PlayerDetailsReceiver {

    private PlayerDetailsService playerDetailsService;

    @Autowired
    public void setPlayerDetailsService(PlayerDetailsService playerDetailsService) {
        this.playerDetailsService = playerDetailsService;
    }

    @RabbitHandler
    public void receive(byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PlayerDetails playerDetails = objectMapper.readValue(bytes, PlayerDetails.class);
            playerDetailsService.saveOrUpdate(playerDetails);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
