package com.wanted_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;

@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
public class WantedServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WantedServerApplication.class, args);
    }
}


