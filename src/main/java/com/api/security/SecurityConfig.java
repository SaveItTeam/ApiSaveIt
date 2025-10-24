package com.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailService userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // 🔓 Endpoints públicos (sem login)
                        .requestMatchers("/login", "/error").permitAll()
                        // 🔒 Swagger só acessível após login
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).authenticated()

                        // 📘 Qualquer requisição GET é leitura (READ ou WRITE)
                        .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("READ", "WRITE")

                        // ✏️ POST, PUT, PATCH e DELETE exigem WRITE
                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("WRITE")
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("WRITE")
                        .requestMatchers(HttpMethod.PATCH, "/api/**").hasRole("WRITE")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("WRITE")

                        // 🔒 Todo o resto exige login
                        .anyRequest().authenticated()
                )

                // 🔐 Login form — redireciona pro Swagger após login
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui.html", true)
                        .permitAll()
                )

                // 🚪 Logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandler)
                );
        return http.build();
    }

}
