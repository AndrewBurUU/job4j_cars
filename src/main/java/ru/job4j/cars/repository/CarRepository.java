package ru.job4j.cars.repository;

import java.util.Collection;

import ru.job4j.cars.model.Car;

public interface CarRepository {

    Collection<Car> findAll();
}
