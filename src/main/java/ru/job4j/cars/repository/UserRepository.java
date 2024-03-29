package ru.job4j.cars.repository;

import ru.job4j.cars.model.User;
import java.util.*;

public interface UserRepository {

    Optional<User> save(User user);

    boolean deleteById(int id);

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findById(int id);

    Collection<User> findAll();
}
