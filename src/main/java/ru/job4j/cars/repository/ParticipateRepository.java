package ru.job4j.cars.repository;

import ru.job4j.cars.model.*;

import java.util.*;

public interface ParticipateRepository {

    Participate save(Participate participate);

    void deleteById(int id);

    Collection<Participate> findAll();

}
