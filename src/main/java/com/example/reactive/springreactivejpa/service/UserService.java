package com.example.reactive.springreactivejpa.service;

import com.example.reactive.springreactivejpa.model.User;
import com.example.reactive.springreactivejpa.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Flux<String> findAll(){
        System.out.println("findAll Call!!!!!!!!!!!");
        return Flux.fromIterable(userRepository.findAll())
                .map(User::getName)
                //.delayElements(Duration.ofSeconds(3))
                .subscribeOn(Schedulers.elastic())
                .log();
    }
}
