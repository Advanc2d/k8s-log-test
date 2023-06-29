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
import org.springframework.web.bind.annotation.PathVariable;


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

    @GetMapping("/redis/set/{key}/{value}")
    public String setRedisValue(@PathVariable String key, @PathVariable String value) {
        redisService.setValue(key, value);
        return "Value set successfully!";
    }

    @GetMapping("/redis/get/{key}")
    public String getRedisValue(@PathVariable String key) {
        String value = redisService.getValue(key);
        return "Value: " + value;
    }

    @GetMapping("/database/save/{name}")
    public @ResponseBody String save(@PathVariable String name) {
        User user = new User();
        user.setName(name);
        user.setEmail(name+"@"+name+".co.kr");
        userRepository.save(user);

        return "Save! " + name + "Complete";
    }

    @GetMapping("/database/list")
    public @ResponseBody Iterable<User> list() {

        return userRepository.findAll();
    }

    // @GetMapping("/database/save/{name}")
    // public @ResponseBody Iterable<User> save(@PathVariable String name) {
    //     User user = new User();
    //     user.setName(name);
    //     user.setEmail(name+"@"+name+".co.kr");
    //     userRepository.save(user);

    //     return userRepository.findAll();
    // }

    // @GetMapping("/database/delete/{name}")
    // public @ResponseBody Iterable<User> delete(@PathVariable String name) {
    //     User user = userRepository.findByName(name);
    //     userRepository.delete(user);

    //     return userRepository.findAll();
    // }
}
