package com.inventory.prosta.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
public class LoadApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoadApplication.class, args);
    }
}
