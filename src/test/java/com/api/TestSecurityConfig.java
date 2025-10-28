package com.api;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;

import static org.mockito.Mockito.mock;

/**
 * Configuração falsa de segurança só para os testes.
 * Impede o erro de AuthenticationConfiguration ausente.
 */
@TestConfiguration
public class TestSecurityConfig {

    @Bean
    @Primary
    public AuthenticationManager authenticationManager() {
        // Retorna um mock para não carregar segurança real
        return mock(AuthenticationManager.class);
    }
}
