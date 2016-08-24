package com.example;

import com.example.domain.Car;
import com.example.repositories.CarRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@SpringBootApplication
public class DataElasticsearchApplication {
    @Bean
    public InitializingBean seedDatabase(CarRepository repository, ElasticsearchTemplate template) {
        return () -> {
//            template.deleteIndex("car");
            repository.deleteAll();
            repository.save(new Car("Honda", "Civic", 1997));
            repository.save(new Car("Honda", "Accord", 2003));
            repository.save(new Car("Ford", "Escort", 1985));
        };
    }

    @Bean
    public CommandLineRunner example(CarRepository repository, ElasticsearchTemplate template) {
        return (args) -> {
            System.err.println("From the repository...");
            repository.findByMakeIgnoringCase("fOrD").forEach(System.err::println);

//            System.err.println("\nFrom the template...");
//            SearchQuery query = new NativeSearchQueryBuilder().withQuery(
//                    moreLikeThisQuery("make").like("Ronda")).build();
//            template.queryForList(query, Car.class).forEach(System.err::println);
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(DataElasticsearchApplication.class, args);
    }
}
