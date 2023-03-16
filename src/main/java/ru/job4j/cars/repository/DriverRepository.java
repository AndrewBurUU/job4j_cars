package ru.job4j.cars.repository;

import java.util.Collection;

import ru.job4j.cars.model.Driver;

public interface DriverRepository {

    Collection<Driver> findAll();
}
