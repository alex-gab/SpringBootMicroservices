package com.example.repositories;

import com.example.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    @RestResource(path="find")
    Iterable<Car> findByMakeIgnoringCase(@Param("make") String make);
}
