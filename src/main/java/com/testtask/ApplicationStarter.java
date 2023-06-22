package com.testtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:spring-beans.xml")
@SpringBootApplication
public class ApplicationStarter {

    public static void main(String... args)
    {
        SpringApplication.run(ApplicationStarter.class, args);
    }

}
