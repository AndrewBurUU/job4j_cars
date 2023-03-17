package ru.job4j.cars.repository;

import ru.job4j.cars.model.*;

import java.util.Collection;

public interface PostRepository {

    Collection<Post> findForLastDay();

    Collection<Post> findWithPhoto();

    Collection<Post> findOnCar(String name);
}
