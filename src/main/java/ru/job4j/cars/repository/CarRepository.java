package ru.job4j.cars.repository;

import java.util.Collection;

import ru.job4j.cars.model.Car;

public interface CarRepository {

    Car save(Car car);

    void deleteById(int id);

    Collection<Car> findAll();
}
