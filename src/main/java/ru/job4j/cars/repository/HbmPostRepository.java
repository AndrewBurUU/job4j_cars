package ru.job4j.cars.repository;

import lombok.*;
import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;

import java.time.*;
import java.util.*;

@Repository
@AllArgsConstructor
public class HbmPostRepository implements PostRepository {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Post> findForLastDay() {
        return crudRepository.query("FROM Post WHERE created >= :fLastDay", Post.class,
                Map.of("fLastDay", LocalDate.now().minusDays(1)));
    }

    @Override
    public Collection<Post> findWithPhoto() {
        return crudRepository.query("SELECT p FROM Post p WHERE p.files.size > 0", Post.class);
    }

    @Override
    public Collection<Post> findOnCar(String name) {
        return crudRepository.query("SELECT p FROM Post p WHERE p.car.name like :fName)", Post.class,
                Map.of("fName", name));
    }
}
