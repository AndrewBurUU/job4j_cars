package ru.job4j.cars.repository;

import java.util.Collection;

import ru.job4j.cars.model.HistoryOwner;

public interface HistoryOwnerRepository {

    HistoryOwner save(HistoryOwner historyOwner);

    void deleteById(int id);

    Collection<HistoryOwner> findAll();
}
