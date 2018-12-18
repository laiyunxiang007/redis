package com.example.redis;

public class RedisPmpSub implements RedisMsg {
    @Override
    public void receiveMessage(String message) {
            System.out.println("PMP接收到了："+message);
        }

    }

