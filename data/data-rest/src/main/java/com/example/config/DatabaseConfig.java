package com.example.config;

import com.example.domain.Car;
import com.example.repositories.CarRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    public InitializingBean seedDatabase(CarRepository repository) {
        return () -> {
            repository.save(new Car("Honda", "Civic", 1997));
            repository.save(new Car("Honda", "Accord", 2003));
            repository.save(new Car("Ford", "Escort", 1985));
        };
    }
}
