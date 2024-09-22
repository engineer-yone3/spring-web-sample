package com.example.yone3.springwebsample.seeder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "com.example.yone3.springwebsample.seeder",
        "com.example.yone3.springwebsample.mapper"
})
public class SeederApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeederApplication.class, args);
    }
}
