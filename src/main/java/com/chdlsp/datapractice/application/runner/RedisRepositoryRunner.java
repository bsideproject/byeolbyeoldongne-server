package com.chdlsp.datapractice.application.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
// public class RedisRepositoryRunner implements ApplicationRunner {
public class RedisRepositoryRunner {


    // TODO: 한 프로젝트에서 여러개의 DB를 사용하려면 어떻게 해야하지?
    // 

    @Autowired
    StringRedisTemplate stringRedisTemplate;

//    @Override
//    public void run(ApplicationArguments args) {
//        ValueOperations<String, String> redisValues = stringRedisTemplate.opsForValue();
//
//        redisValues.set("db", "Redis");
//        redisValues.set("type", "test");
//
//        log.info("redisValues db : " + redisValues.get("db"));
//        log.info("redisValues db : " + redisValues.get("type"));

//        User user = User.builder()
//                .email("tester@example.com")
//                .build();

//    }
}
