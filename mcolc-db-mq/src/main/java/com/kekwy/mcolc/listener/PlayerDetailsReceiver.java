package com.kekwy.mcolc.listener;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kekwy.mcolc.mapper.PlayerDetailsMapper;
import com.kekwy.mcolc.model.PlayerDetails;
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

    private PlayerDetailsMapper playerDetailsMapper;

    @Autowired
    public void setPlayerDetailsMapper(PlayerDetailsMapper playerDetailsMapper) {
        this.playerDetailsMapper = playerDetailsMapper;
    }


    @RabbitHandler
    public void receive(byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PlayerDetails playerDetails = objectMapper.readValue(bytes, PlayerDetails.class);
            playerDetailsMapper.insert(playerDetails);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
