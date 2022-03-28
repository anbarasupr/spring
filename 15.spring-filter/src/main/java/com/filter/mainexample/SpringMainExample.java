package com.filter.mainexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(scanBasePackages={"com.filter.*"})
public class SpringMainExample {
    public static void main(final String[] args) {
        final ConfigurableApplicationContext configurableApplicationContext = SpringApplication
                .run(SpringMainExample.class, args);
    }
}