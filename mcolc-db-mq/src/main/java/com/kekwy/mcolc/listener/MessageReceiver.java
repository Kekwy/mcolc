package com.kekwy.mcolc.listener;


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
public class MessageReceiver {

    @RabbitHandler
    public void deliver(String jsonData) {

    }

}
