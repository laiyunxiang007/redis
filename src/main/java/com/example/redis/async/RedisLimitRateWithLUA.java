package com.example.redis.async;

import com.example.redis.util.FileUtil;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RedisLimitRateWithLUA {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        for (int i = 0; i < 7; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        countDownLatch.await();
                        System.out.println("请求是否被执行："+accquire());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

        countDownLatch.countDown();
    }

    public static boolean accquire() {
       Jedis jedis = new Jedis("39.98.94.81",6379);
       jedis.auth("123456");
       FileUtil fileUtil=new FileUtil();
        String luaScript=fileUtil.readToString("F:\\redis\\src\\main\\resources\\limitRate.lua");
//        String luaScript = "local key =KEYS[1]\n" +
//                "local limit = tonumber(ARGV[1])\n" +
//                "local current = tonumber(redis.call('get', key) or \"0\")\n" +
//                "if current + 1 > limit then\n" +
//                "    return 0\n" +
//                "else\n" +
//                "    redis.call(\"INCRBY\", key, \"1\");\n" +
//                "    redis.call(\"expire\", key, \"2\");\n" +
//                "end\n" +
//                "return 1";

        String key = "ip:" + System.currentTimeMillis()/1000; // 当前秒
//        String key="lyx";
        String limit = "5"; // 最大限制
        String expire_time = "5"; // 最大限制
        List<String> keys = new ArrayList<String>();
        keys.add(key);
        List<String> args = new ArrayList<String>();
        args.add(limit);
        args.add(expire_time);
        Long result = (Long)(jedis.eval(luaScript, keys, args)); // 执行lua脚本，传入参数
        return result == 1;
    }
}


