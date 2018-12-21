package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//定时器
@EnableScheduling
@Component
public class TestSenderController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    //向redis消息队列index通道发布消息
    @Scheduled(fixedRate = 5000)

    public void sendMessage() {
        stringRedisTemplate.convertAndSend("pmp", String.valueOf(Math.random() * 10));
        stringRedisTemplate.convertAndSend("channel", String.valueOf(Math.random() * 10));
    }
}
