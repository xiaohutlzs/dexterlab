package com.dexterlab.crm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "prelogin")
public class PreLoginController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String preLogin(HttpServletRequest request) {
        log.info(request.toString());
        DefaultCsrfToken token = (DefaultCsrfToken) request.getAttribute("_csrf");
        if (token == null) {
            throw new RuntimeException("could not get a token.");
        }
        return token.getToken();
    }

}
