package com.example.services;

import org.springframework.stereotype.Service;

@Service
public final class EchoService {
    public final String echo(final String input) {
        return "Echo: " + input;
    }
}
