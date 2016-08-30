package com.example.consumer;

import com.example.dto.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.example.MessagingApp.NOTIFICATIONS;

@Component
public final class Consumer {
    @RabbitListener(queues = NOTIFICATIONS)
    public final void onNotification(Message<Notification> notification) {
        System.out.println("received " + notification.toString());
        System.out.println("received payload " + notification.getPayload());
        System.out.println("received headers " + notification.getHeaders().toString());
    }
}
