package com.dexterlab.crm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Auth {
    @GetMapping("logout")
    void logOut(){

    }
}
