package ru.job4j.cars.repository;

import ru.job4j.cars.model.*;

import java.util.*;

public interface PostRepository {

    Post save(Post post);

    void update(Post post);

    Optional<Post> findById(int id);

    Collection<Post> findForLastDay();

    Collection<Post> findWithPhoto();

    Collection<Post> findOnCar(String name);

    Collection<Post> findAll();

    boolean deleteById(int id);
}
