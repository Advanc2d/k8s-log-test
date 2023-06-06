package com.example.k8slogtest.controller;

import com.example.k8slogtest.repository.UserRepository;
import com.example.k8slogtest.service.RedisService;
import com.example.k8slogtest.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@Slf4j
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    private RedisService redisService;
    private UserRepository userRepository;

    @Autowired
    public TestController(RedisService redisService, UserRepository userRepository) {
        this.redisService = redisService;
        this.userRepository = userRepository;
    }

    @GetMapping("/log")
    public String logTest() {
        String podName = System.getenv("HOSTNAME");
        log.info("파드 이름: " + podName);
        logger.trace("This is an trace log message");
        logger.debug("This is an debug log message");
        logger.info("This is an info log message : " + podName);
        logger.warn("This is an warn log message");
        logger.error("This is an error log message");

        return "log Test 완료! in " + podName;
    }

    @GetMapping("/redis")
    public String getRedisStringValue(/*@RequestBody HashMap<String, String> body*/) {
        redisService.setValues("name", "박현민"/*body.get("name"), body.get("age")*/);
        String podName = System.getenv("HOSTNAME");
        log.info("파드 이름: " + podName);
        log.info("redis Test : " + redisService.getValues("name"));

        return "redis Test 완료! in " + podName;
    }

    @GetMapping("/database")
    public @ResponseBody Iterable<User> database() {
        User user = new User();
        user.setName("k8s");
        user.setEmail("k8s@k8s.com");
        userRepository.save(user);

        return userRepository.findAll();
    }
}
