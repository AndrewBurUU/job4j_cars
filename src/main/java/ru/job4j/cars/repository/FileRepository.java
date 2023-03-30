package ru.job4j.cars.repository;

import java.util.*;

import ru.job4j.cars.model.*;

public interface FileRepository {

    File save(File file);

    boolean update(File file);

    Optional<File> findById(int id);

    void deleteById(int id);

    Collection<File> findAll();

}
