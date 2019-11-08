package com.example.pm.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public abstract class WebMvcConfig implements WebMvcConfigurer {

    private static final String RESOURCE_HANDLER_WEBJARS = "/webjars/**";
    private static final String[] RESOURCE_LOCATIONS_WEBJARS = {
            "classpath:/META-INF/resources/webjars/"};

    private static final String RESOURCE_HANDLER_OTHERS = "/*";
    private static final String[] RESOURCE_LOCATIONS_OTHERS = {
            "classpath:/resources/",
            "classpath:/static/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(RESOURCE_HANDLER_WEBJARS)
                .addResourceLocations(RESOURCE_LOCATIONS_WEBJARS);
        registry
                .addResourceHandler(RESOURCE_HANDLER_OTHERS)
                .addResourceLocations(RESOURCE_LOCATIONS_OTHERS);

    }
}
