package com.api.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String renderEnv = System.getenv("RENDER");

        if (renderEnv != null) {
            // Caminho persistente
            registry.addResourceHandler("/uploads/**")
                    .addResourceLocations("file:/data/uploads/");
        } else {
            // Caminho local
            registry.addResourceHandler("/uploads/**")
                    .addResourceLocations("file:uploads/");
        }
    }
}
