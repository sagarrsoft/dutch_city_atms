package com.mobiquity.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class SpringBootStartApplication extends SpringBootServletInitializer {

    public static void main(String args[]) throws Exception {
        SpringApplication.run(SpringBootStartApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootStartApplication.class);
    }

}
