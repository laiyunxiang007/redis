package com.example.redis;

import com.example.redis.entity.ZaZhi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class testService {

    @Autowired
    private ZaZhi z;

    @Autowired
    private RedisTemplate redisTemplate;

    public String count(){
       HashOperations<String,Object,Object> hashoperations = (HashOperations<String, Object, Object>) redisTemplate.boundHashOps("car");

        hashoperations.put("car","name","BMW");
        hashoperations.put("car","color","blue");
        hashoperations.put("car","size","1.5T");
        hashoperations.put("car1","name","BMW");
        hashoperations.put("car1","color","blue");
        hashoperations.put("car1","size","1.5T");
        hashoperations.put("car","name",hashoperations.get("car","name")+"你好");
        ListOperations<String,Object> listOperations= (ListOperations<String, Object>) redisTemplate.boundListOps("name");

        listOperations.leftPush("name","car1");
        listOperations.leftPush("name","car2");
        listOperations.leftPush("name","car");


        System.out.println(listOperations.range("name", 0, 0));
        System.out.println(listOperations.range("name", 1, 2));
//        System.out.println(hashoperations.keys("car")+","+hashoperations.values("car"));
        redisTemplate.expire("car",10,TimeUnit.SECONDS);
        redisTemplate.expire("name",10,TimeUnit.SECONDS);
        redisTemplate.expire("car1",10,TimeUnit.SECONDS);
        return String.valueOf(1);
    }




}
