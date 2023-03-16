package ru.job4j.cars.repository;

import lombok.*;
import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmDriverRepository implements DriverRepository {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Driver> findAll() {
        return crudRepository.query("FROM Driver", Driver.class);
    }
}
