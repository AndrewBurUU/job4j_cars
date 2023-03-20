package ru.job4j.cars.repository;

import lombok.*;
import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmFileRepository implements FileRepository {

    private final CrudRepository crudRepository;

    @Override
    public File save(File file) {
        crudRepository.run(session -> session.persist(file));
        return file;
    }

    @Override
    public void deleteById(int id) {
        crudRepository.run("DELETE File WHERE id = :fId",
                Map.of("fId", id));
    }

    @Override
    public Collection<File> findAll() {
        return crudRepository.query("FROM File", File.class);
    }
}
