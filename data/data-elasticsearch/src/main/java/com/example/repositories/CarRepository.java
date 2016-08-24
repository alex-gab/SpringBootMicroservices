package com.example.repositories;

import com.example.domain.Car;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CarRepository extends ElasticsearchRepository<Car, Long> {
    Iterable<Car> findByMakeIgnoringCase(String make);
}
