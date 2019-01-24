package com.bsu.avizhen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class SocialNetworkApp extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SocialNetworkApp.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SocialNetworkApp.class, args);
    }
}
