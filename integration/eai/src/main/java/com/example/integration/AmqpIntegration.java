package com.example.integration;

import com.example.services.EchoService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;

import java.util.Arrays;

@Configuration
public class AmqpIntegration {
    private final String echoQueueAndExchangeName;

    public AmqpIntegration(@Value("${echo.amqp.queue:echo}") final String echoQueueAndExchangeName) {
        this.echoQueueAndExchangeName = echoQueueAndExchangeName;
    }

    @Bean
    @SuppressWarnings("Duplicates")
    public InitializingBean prepareQueues(final AmqpAdmin amqpAdmin) {
        return () -> {
            final Queue queue = new Queue(echoQueueAndExchangeName, true);
            final DirectExchange exchange = new DirectExchange(echoQueueAndExchangeName);
            final Binding binding =
                    BindingBuilder.
                            bind(queue).
                            to(exchange).
                            with(echoQueueAndExchangeName);
            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareExchange(exchange);
            amqpAdmin.declareBinding(binding);
        };
    }

    @Bean
    public IntegrationFlow amqpReplyFlow(final ConnectionFactory rabbitConnectionFactory,
                                         final EchoService echoService) {
        return IntegrationFlows.
                from(Amqp.inboundGateway(rabbitConnectionFactory, echoQueueAndExchangeName)).
                transform(String.class, echoService::echo).
                get();
    }

    @Bean
    public CommandLineRunner client(final RabbitTemplate template) {
        return args ->
                Arrays.asList("Josh", "Phil").
                        forEach(n ->
                                System.out.println("template response: " +
                                        template.convertSendAndReceive(echoQueueAndExchangeName, n)));
    }
}
