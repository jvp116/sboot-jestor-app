package com.jestor;

import com.jestor.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class Application {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        var app = new SpringApplication(Application.class);
        app.run(args);
    }

}
