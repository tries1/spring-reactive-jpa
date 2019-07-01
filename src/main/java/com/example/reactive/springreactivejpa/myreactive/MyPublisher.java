package com.example.reactive.springreactivejpa.myreactive;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyPublisher implements Publisher {

    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    public void subscribe(Subscriber s) {
        log.info("publisher - subscribe");

        s.onSubscribe(new MySubscription(s, executorService));
    }
}
