package ru.job4j.cars.service;

import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.*;

import java.util.*;

@Service
public class HbmCarService implements CarService {

    private final CarRepository carRepository;

    public HbmCarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Collection<Car> findAll() {
        return carRepository.findAll();
    }
}
