package ru.job4j.cars.repository;

import java.util.Collection;

import ru.job4j.cars.model.File;

public interface FileRepository {

    File save(File file);

    void deleteById(int id);

    Collection<File> findAll();

}
