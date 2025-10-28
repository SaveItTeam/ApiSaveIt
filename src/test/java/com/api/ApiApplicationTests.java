package com.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=test")
class ApiApplicationTests {

    @Test
    void contextLoads() {
        // Apenas valida se o contexto carrega com H2
    }
}
