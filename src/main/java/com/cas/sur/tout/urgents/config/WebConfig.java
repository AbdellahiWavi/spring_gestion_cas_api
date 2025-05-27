package com.cas.sur.tout.urgents.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.cas.sur.tout.urgents.utils.Constants.IMAGE_ENDPOINT;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(IMAGE_ENDPOINT + "/**")
                .addResourceLocations("file:uploads/");
    }
}
