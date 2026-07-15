package com.shop.aifruit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/index", "/fruit/manage", "/fruit/ai", "/stats/stock")
                .excludePathPatterns("/login", "/static/**", "/h2-console/**", "/fruit/page");
    }
}