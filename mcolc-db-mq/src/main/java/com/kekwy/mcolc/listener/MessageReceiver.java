package com.kekwy.mcolc.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = "mcolc.db.queue", autoDelete = "false"),
                exchange = @Exchange(value = "mcolc.db.direct"),
                key = "mcolc.db.key"
        )
)
@Slf4j
public class MessageReceiver {

    @RabbitHandler
    public void deliver(byte[] bytes) {
        log.info(new String(bytes));
    }

}
