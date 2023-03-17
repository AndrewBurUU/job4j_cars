package ru.job4j.cars.repository;

import lombok.*;
import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmPostRepository implements PostRepository {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Post> findForLastDay() {
        return crudRepository.query("FROM Post WHERE created >= "
                + "(SELECT DATE_TRUNC('day', max(created)) FROM Post)", Post.class);
    }

    @Override
    public Collection<Post> findWithPhoto() {
        return crudRepository.query("FROM Post WHERE id in "
                + "(SELECT id FROM File)", Post.class);
    }

    @Override
    public Collection<Post> findOnCar(String name) {
        return crudRepository.query("FROM Post WHERE car_id in (SELECT id FROM Car WHERE name like :fName)", Post.class,
                Map.of("fName", name));
    }
}
