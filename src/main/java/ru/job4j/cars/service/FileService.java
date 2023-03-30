package ru.job4j.cars.service;

import ru.job4j.cars.model.*;

import java.util.*;

public interface FileService {

    File save(File file);

    boolean update(File file);

    Collection<File> findAll();

}
