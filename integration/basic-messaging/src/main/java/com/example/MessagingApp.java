package com.example;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessagingApp {

    public static final String NOTIFICATIONS = "notifications";

    public static void main(String[] args) {
        SpringApplication.run(MessagingApp.class, args);
    }

    @Bean
    @SuppressWarnings("Duplicates")
    public InitializingBean prepareQueues(AmqpAdmin amqpAdmin) {
        return () -> {
            final Queue queue = new Queue(NOTIFICATIONS, true);
            final DirectExchange exchange = new DirectExchange(NOTIFICATIONS);
            final Binding binding =
                    BindingBuilder.
                            bind(queue).
                            to(exchange).
                            with(NOTIFICATIONS);
            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareExchange(exchange);
            amqpAdmin.declareBinding(binding);
        };
    }
}
