package com.xivs.weblab44;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.xivs.weblab44.repository")

public class WebLab44Application {

    public static void main(String[] args) {
        SpringApplication.run(WebLab44Application.class, args);
    }

}
