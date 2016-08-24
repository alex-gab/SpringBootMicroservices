package com.example.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "car", type = "car", shards = 1, replicas = 0, refreshInterval = "-1")
public final class Car {
    @Id
    private Long id;
    private String make;
    private String model;
    private int year;

    Car() {
    }

    public Car(String make, String model, int year) {
        super();
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return make + " " + model + " " + year;
    }
}
