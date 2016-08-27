package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class Car {
    @Id
    @GeneratedValue
    private long id;

    private String make;

    private String model;

    private int year;

    public Car(final String make, final String model, final int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    Car() {
    }

    public final String getMake() {
        return make;
    }

    public final String getModel() {
        return model;
    }

    public final int getYear() {
        return year;
    }

    @Override
    public final String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
