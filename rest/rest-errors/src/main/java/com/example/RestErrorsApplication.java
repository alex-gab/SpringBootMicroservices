package com.example;

import com.example.dbo.Person;
import com.example.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class RestErrorsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestErrorsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(PersonRepository personRepository) {
        return args -> {
            Arrays.asList("Phil", "Josh").forEach(name ->
                    personRepository.save(new Person(name, (name + "@email.com").toLowerCase()))
            );
            personRepository.findAll().forEach(System.out::println);
        };
    }
}
