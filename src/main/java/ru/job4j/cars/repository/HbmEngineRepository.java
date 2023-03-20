package ru.job4j.cars.repository;

import lombok.*;
import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmEngineRepository implements EngineRepository {

    private final CrudRepository crudRepository;

    @Override
    public Engine save(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
        return engine;
    }

    @Override
    public Collection<Engine> findAll() {
        return crudRepository.query("FROM Engine", Engine.class);
    }

    @Override
    public void deleteById(int id) {
        crudRepository.run("DELETE Engine WHERE id = :fId",
                Map.of("fId", id));
    }
}
