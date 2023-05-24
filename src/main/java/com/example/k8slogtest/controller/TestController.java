package com.example.k8slogtest.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @GetMapping("/log")
    public String logTest() {
        logger.trace("This is an trace log message");
        logger.debug("This is an debug log message");
        logger.info("This is an info log message");
        logger.warn("This is an warn log message");
        logger.error("This is an error log message");

        return "log Test 완료!";
    }
}
