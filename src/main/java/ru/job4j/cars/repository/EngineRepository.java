package ru.job4j.cars.repository;

import java.util.Collection;

import ru.job4j.cars.model.Engine;

public interface EngineRepository {

    Collection<Engine> findAll();
}
