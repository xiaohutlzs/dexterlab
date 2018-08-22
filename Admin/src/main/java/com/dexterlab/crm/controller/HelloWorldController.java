package com.dexterlab.crm.controller;

import com.dexterlab.crm.core.security.SimpleLoginUser;
import com.dexterlab.crm.domain.entity.User;
import com.dexterlab.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
@RequestMapping(path = "hello")
@RestController
public class HelloWorldController {

    @Autowired
    private UserService userService;

    public Mono<String> HelloWorld() {
        return Mono.just("Hello World!");
    }

//    @PostMapping
//    public int addUser(UserDomain user){
//        return userService.addUser(user);
//    }

    @GetMapping(path = "alluser")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @PostMapping
    public void saveUser(@AuthenticationPrincipal SimpleLoginUser loginUser, @RequestBody User user){
        userService.insert(user);
    }
}
