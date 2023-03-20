package ru.job4j.cars.repository;

import lombok.*;
import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmHistoryOwnerRepository implements HistoryOwnerRepository {

    private final CrudRepository crudRepository;

    @Override
    public HistoryOwner save(HistoryOwner historyOwner) {
        crudRepository.run(session -> session.persist(historyOwner));
        return historyOwner;
    }

    @Override
    public void deleteById(int id) {
        crudRepository.run("DELETE HistoryOwner WHERE id = :fId",
                Map.of("fId", id));
    }

    @Override
    public Collection<HistoryOwner> findAll() {
        return crudRepository.query("FROM HistoryOwner", HistoryOwner.class);
    }
}
