package com.api;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;

@TestConfiguration
public class SecurityTestConfig {

    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> authentication; // simples mock
    }
}
