package com.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(properties = "spring.profiles.active=test")
@Import(SecurityTestConfig.class)
class ApiApplicationTests {
    @Test
    void contextLoads() {
        // Apenas valida se o contexto carrega com H2
    }
}
