package com.example.todolistproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class ToDoLIstProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoLIstProjectApplication.class, args);
    }


}
