package com.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Value("${api.token}")
    private String apiToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Ignora o Swagger, login, logout e endpoints públicos
        if (path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars")
                || path.startsWith("/login")
                || path.startsWith("/logout")
                || path.startsWith("/error")
                || path.startsWith("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        boolean validToken = false;

        if (header != null && (header.equals("Bearer " + apiToken) || header.equals(apiToken))) {
            validToken = true;

            var authentication = new UsernamePasswordAuthenticationToken(
                    "api-token-user",
                    null,
                    List.of(
                            new SimpleGrantedAuthority("ROLE_READ"),
                            new SimpleGrantedAuthority("ROLE_WRITE")
                    )
            );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        request.setAttribute("validToken", validToken);
        filterChain.doFilter(request, response);
    }
}
