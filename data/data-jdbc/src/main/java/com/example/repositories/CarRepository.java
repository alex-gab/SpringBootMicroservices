package com.example.repositories;


import com.example.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public CarRepository(final JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Car> findByMakeIgnoringCase(String make) {
        return jdbc.query(
                "select * from car "
                        + "where UPPER(car.make) = UPPER(?) order by id",
                (rs, i) -> new Car(
                        rs.getInt("id"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year")), make);
    }
}
