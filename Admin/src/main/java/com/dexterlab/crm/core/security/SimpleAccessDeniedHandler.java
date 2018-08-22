package com.dexterlab.crm.core.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleAccessDeniedHandler implements AccessDeniedHandler {
    Logger log = LoggerFactory.getLogger(this.getClass());
    public SimpleAccessDeniedHandler() {
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException exception) throws IOException, ServletException {
        if (response.isCommitted()) {
            log.info("Response has already been committed.");
            return;
        }
        dump(exception);
        response.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
    }

    private void dump(AccessDeniedException e) {
        if (e instanceof AuthorizationServiceException) {
            log.debug("AuthorizationServiceException : {}", e.getMessage());
        } else if (e instanceof CsrfException) {
            log.debug("org.springframework.security.web.csrf.CsrfException : {}", e.getMessage());
        } else if (e instanceof org.springframework.security.web.server.csrf.CsrfException) {
            log.debug("org.springframework.security.web.server.csrf.CsrfException : {}", e.getMessage());
        } else {
            log.debug("AccessDeniedException : {}", e.getMessage());
        }
    }

}