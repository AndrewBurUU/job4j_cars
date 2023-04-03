package ru.job4j.cars.repository;

import lombok.*;
import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

@Repository
@AllArgsConstructor
public class HbmPostRepository implements PostRepository {

    private final CrudRepository crudRepository;

    @Override
    public Post save(Post post) {
        crudRepository.run(session -> session.persist(post));
        return post;
    }

    @Override
    public void update(Post post) {
        crudRepository.run(session -> session.merge(post));
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional("from Post p LEFT JOIN FETCH p.files where p.id = :fId", Post.class,
                Map.of("fId", id));
    }

    @Override
    public Collection<Post> findForLastDay() {
        return crudRepository.query("FROM Post WHERE created >= :fLastDay", Post.class,
                Map.of("fLastDay", LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).minusDays(1)));
    }

    @Override
    public Collection<Post> findWithPhoto() {
        return crudRepository.query("SELECT p FROM Post p WHERE p.files.size > 0", Post.class);
    }

    @Override
    public Collection<Post> findOnCar(String name) {
        return crudRepository.query("SELECT p FROM Post p WHERE p.car.name like :fName", Post.class,
                Map.of("fName", name));
    }

    @Override
    public Collection<Post> findAll() {
        return crudRepository.query("FROM Post", Post.class);
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.run("DELETE Post WHERE id = :fId",
                Map.of("fId", id)) > 0;
    }

}
