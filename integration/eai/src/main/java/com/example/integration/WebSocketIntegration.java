package com.example.integration;

import com.example.services.EchoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.integration.dsl.support.Function;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.websocket.ServerWebSocketContainer;
import org.springframework.integration.websocket.outbound.WebSocketOutboundMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Configuration
@RestController
public class WebSocketIntegration {

    @Bean
    public ServerWebSocketContainer serverWebSocketContainer() {
        return new ServerWebSocketContainer("/messages").withSockJs();
    }

    @Bean
    public MessageHandler webSocketOutboundAdapter() {
        return new WebSocketOutboundMessageHandler(serverWebSocketContainer());
    }

    @Bean(name = "webSocketFlow.input")
    public MessageChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean
    public Function<String, Object> echoSplitter(final EchoService echoService) {
        return messagePayload ->
                serverWebSocketContainer().
                        getSessions().
                        keySet().
                        stream().
                        map(s ->
                                MessageBuilder.
                                        withPayload(echoService.echo(messagePayload)).
                                        setHeader(SimpMessageHeaderAccessor.SESSION_ID_HEADER, s).
                                        build()).
                        collect(Collectors.toList());
    }

    @Bean
    public IntegrationFlow webSocketFlow(final Function<String, Object> echoSplitter) {
        return (IntegrationFlowDefinition<?> integrationFlowDefinition) ->
                integrationFlowDefinition.split(String.class, echoSplitter).
                        channel(c -> c.executor(Executors.newCachedThreadPool())).
                        handle(webSocketOutboundAdapter());
    }

    @RequestMapping("/echo")
    public final void send(@RequestParam String name) {
        requestChannel().send(MessageBuilder.withPayload(name).build());
    }
}
