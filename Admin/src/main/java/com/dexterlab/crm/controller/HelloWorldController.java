package com.dexterlab.crm.controller;

import com.dexterlab.crm.domain.entity.User;
import com.dexterlab.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloWorldController {

    @Autowired
    private UserService userService;

    @GetMapping("hello")
    public Mono<String> HelloWorld() {
        return Mono.just("Hello World!");
    }

//    @PostMapping
//    public int addUser(UserDomain user){
//        return userService.addUser(user);
//    }

    @GetMapping
    public Object findAllUser(){
        return userService.findAllUser();
    }

    @PostMapping
    public void saveUser(@RequestBody User user){
        userService.insert(user);
    }
}
