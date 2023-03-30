package ru.job4j.cars.service;

import ru.job4j.cars.model.*;

import java.util.*;

public interface UserService {

    Optional<User> save(User user);

    boolean deleteById(int id);

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findById(int id);

    Collection<User> findAll();

}
