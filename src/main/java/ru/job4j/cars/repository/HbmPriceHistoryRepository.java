package ru.job4j.cars.repository;

import lombok.*;
import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmPriceHistoryRepository implements PriceHistoryRepository {

    private final CrudRepository crudRepository;

    @Override
    public PriceHistory save(PriceHistory priceHistory) {
        crudRepository.run(session -> session.persist(priceHistory));
        return priceHistory;
    }

    @Override
    public void deleteById(int id) {
        crudRepository.run("DELETE PriceHistory WHERE id = :fId",
                Map.of("fId", id));
    }

    @Override
    public Collection<PriceHistory> findAll() {
        return crudRepository.query("FROM PriceHistory", PriceHistory.class);
    }
}
