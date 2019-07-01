package com.example.reactive.springreactivejpa.myreactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySubscriber implements Subscriber {

    private Integer count = 0;
    private final Integer DEMAND_COUNT = 3;
    private Subscription mySubscription;

    @Override
    public void onSubscribe(Subscription s) {

        count = DEMAND_COUNT;
        this.mySubscription = s;
        this.mySubscription.request(DEMAND_COUNT);
    }

    @Override
    public void onNext(Object o) {
        synchronized (this) {
            count--;
            if (count == 0) {
                log.info("count is zero");
                count = DEMAND_COUNT;
                mySubscription.request(count);
            }
        }
    }

    @Override
    public void onError(Throwable t) {
        log.info("subscriber - onError");
    }

    @Override
    public void onComplete() {
        log.info("subscriber - onComplete");
    }
}
