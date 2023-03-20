package ru.job4j.cars.repository;

import lombok.*;
import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmParticipateRepository implements ParticipateRepository {

    private final CrudRepository crudRepository;

    @Override
    public Participate save(Participate participate) {
        crudRepository.run(session -> session.persist(participate));
        return participate;
    }

    @Override
    public void deleteById(int id) {
        crudRepository.run("DELETE Participate WHERE id = :fId",
                Map.of("fId", id));
    }

    @Override
    public Collection<Participate> findAll() {
        return crudRepository.query("FROM Participate p JOIN FETCH p.post", Participate.class);
    }
}
