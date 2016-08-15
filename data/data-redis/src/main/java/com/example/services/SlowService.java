package com.example.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SlowService {
    @Cacheable("slow")
    public String execute(String arg) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        return UUID.randomUUID().toString();
    }
}
