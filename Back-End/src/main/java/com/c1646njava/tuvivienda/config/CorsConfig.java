package com.c1646njava.tuvivienda.config;


import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){

        registry.addMapping("/")
                .allowedOriginPatterns("")
                .allowedMethods("")
                .allowedHeaders("")
                .allowCredentials(false);
    }
}