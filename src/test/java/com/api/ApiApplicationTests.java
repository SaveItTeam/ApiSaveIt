package com.api;

import com.api.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestSecurityConfig.class)
class ApiApplicationTests {

    @Test
    void contextLoads() {
        // Só verifica se o contexto carrega corretamente
    }
}
