package com.example.redis;

import org.springframework.stereotype.Component;

@Component
public interface RedisMsg {
    public void receiveMessage(String message);
}
