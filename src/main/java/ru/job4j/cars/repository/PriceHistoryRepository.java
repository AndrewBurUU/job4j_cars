package ru.job4j.cars.repository;

import ru.job4j.cars.model.*;

import java.util.*;

public interface PriceHistoryRepository {

    PriceHistory save(PriceHistory priceHistory);

    void deleteById(int id);

    Collection<PriceHistory> findAll();

}
