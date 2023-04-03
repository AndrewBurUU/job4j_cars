package ru.job4j.cars.service;

import ru.job4j.cars.model.*;

import java.util.*;

public interface PostService {

    Post save(Post post);

    void update(Post post);

    Optional<Post> findById(int id);

    Collection<Post> findForLastDay();

    Collection<Post> findWithPhoto();

    Collection<Post> findOnCar(String name);

    Collection<Post> findAll();

    boolean deleteById(int id);

}
