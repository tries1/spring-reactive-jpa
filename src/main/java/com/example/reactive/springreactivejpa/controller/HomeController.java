package com.example.reactive.springreactivejpa.controller;

import com.example.reactive.springreactivejpa.dto.UserDto;
import com.example.reactive.springreactivejpa.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @PostMapping("api/users")
    public Mono<String> save(@RequestBody UserDto userDto){
        System.out.println(userDto);

        return Mono.just("success");
    }


    @GetMapping("/")
    public Flux<String> home() {
        log.info("home!!!!!!");
        return userService.findAll();
    }

    @GetMapping("test")
    public Flux<Integer> test() {
        log.info("test!!!!!!");

        Flux<Integer> flux = Flux.range(1, 10).delayElements(Duration.ofMillis(1000)).log();
        //flux.doOnNext(i -> log.info("next " + i)).doOnComplete(() -> log.info("complete")).subscribeOn(Schedulers.elastic());

        flux
                .take(3)
                .doOnNext(i -> System.out.println("next " + i))
                .doOnCancel(() -> System.out.println("cancel"))
                .doOnComplete(() -> System.out.println("complete"))
                .subscribe();

        return flux
                .take(3)
                .doOnNext(i -> System.out.println("next " + i))
                .doOnCancel(() -> System.out.println("cancel"))
                .doOnComplete(() -> System.out.println("complete"));
                //.subscribeOn(Schedulers.elastic());
    }

    @GetMapping("user")
    public Mono<UserDto> userDtoMono() {
        return Mono.just(UserDto.builder().age(12).build());
    }
}
