package ru.geekbrains.musicportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.geekbrains.musicportal.config.AdditionalConfiguration;
import ru.geekbrains.musicportal.security.SecurityConfiguration;

//@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "ru.geekbrains.musicportal")
@Import({AdditionalConfiguration.class, SecurityConfiguration.class})
@EnableJpaRepositories({"ru.geekbrains.musicportal.repository"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
