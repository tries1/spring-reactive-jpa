package com.example.reactive.springreactivejpa.controller;

import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

//퍼사드 패턴

//Simple Logging Facede 4 Java
//log4j -> logback, slf4j
@Slf4j
@RestController
@RequestMapping("api/log")
public class LogController {
    public void aaa() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);


        //IntStream.rangeClosed(1, 10).forEach(i -> test2(i));
        //IntStream.rangeClosed(1, 10).forEach(i -> executorService.execute(() -> test2(i)));
        IntStream.rangeClosed(1, 10).forEach(i -> executorService2.execute(() -> test2(i)));
    }

    public void test2(int i) {
        try {
            Thread.sleep(1000);
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("test")
    public Mono<String> log() {
        MDC.put("key", "A");
        MDC.put("userId", String.valueOf((Math.random() * 1000)));
        log.info("hello");
        MDC.clear();

        return Mono.just("success");
    }
}
