package com.example.reactive.springreactivejpa.myreactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySubscription implements Subscription {

    private final ExecutorService executorService;
    private Subscriber subscriber;
    private final AtomicInteger value;

    MySubscription(Subscriber subscriber, ExecutorService executorService) {
        this.subscriber = subscriber;
        this.executorService = executorService;

        value = new AtomicInteger();
    }

    @Override
    public void request(long n) {
        if (n < 0) {//TODO:error
        } else {
            for (int i = 0; i < n; i++) {
                //new Runnable()
                executorService.execute(() -> {
                    int count = value.incrementAndGet();

                    if (count > 1000) {
                        log.info("Item is over ");
                        subscriber.onComplete();
                    } else {
                        log.info("push Item + " + count);
                        subscriber.onNext(count);
                    }
                });
            }
        }
    }

    @Override
    public void cancel() {
        subscriber.onComplete();
    }


}