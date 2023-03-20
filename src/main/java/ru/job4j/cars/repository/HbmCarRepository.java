package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmCarRepository implements CarRepository {

    private final CrudRepository crudRepository;

    @Override
    public Car save(Car car) {
        crudRepository.run(session -> session.persist(car));
        return car;
    }

    @Override
    public void deleteById(int id) {
        crudRepository.run("DELETE Car WHERE id = :fId",
                Map.of("fId", id));
    }

    @Override
    public Collection<Car> findAll() {
        return crudRepository.query("FROM Car", Car.class);
    }
}
