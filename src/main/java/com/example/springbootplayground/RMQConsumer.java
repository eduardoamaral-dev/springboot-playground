package com.example.springbootplayground;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RMQConsumer {

    @RabbitListener(queues = "techdrops.queue.example", autoStartup = "true")
    public void consumeMessage(String message) {
        System.out.println("[MENSAGEM RECEBIDA]  " + message);
    }
}
