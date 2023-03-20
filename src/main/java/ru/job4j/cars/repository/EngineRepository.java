package ru.job4j.cars.repository;

import java.util.Collection;

import ru.job4j.cars.model.Engine;

public interface EngineRepository {

    Engine save(Engine engine);

    Collection<Engine> findAll();

    void deleteById(int id);
}
