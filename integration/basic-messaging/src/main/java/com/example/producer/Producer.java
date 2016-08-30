package com.example.producer;

import com.example.dto.Notification;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.example.MessagingApp.NOTIFICATIONS;

@Component
public final class Producer implements CommandLineRunner {
    private final RabbitMessagingTemplate messagingTemplate;

    @Autowired
    public Producer(final RabbitMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public final void run(final String... strings) throws Exception {
        final Notification notification = new Notification(
                UUID.randomUUID().toString(), "Hello, world!", new Date());

        Map<String, Object> headers = new HashMap<>();
        headers.put("notification-id", notification.getId());

        messagingTemplate.convertAndSend(
                NOTIFICATIONS,
                notification,
                headers,
                message -> {
                    System.out.println("sending " + message.getPayload().toString());
                    return message;
                });
    }
}
