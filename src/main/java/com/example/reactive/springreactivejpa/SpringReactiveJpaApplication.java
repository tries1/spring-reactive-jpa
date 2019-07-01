package com.example.reactive.springreactivejpa;

import com.example.reactive.springreactivejpa.myreactive.MyPublisher;
import com.example.reactive.springreactivejpa.myreactive.MySubscriber;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringReactiveJpaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveJpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Publisher publisher = new MyPublisher();
        Subscriber subscriber = new MySubscriber();
        publisher.subscribe(subscriber);

    }
}
