package ru.job4j.cars.service;

import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.*;

import java.util.*;

@Service
public class HbmEngineService implements EngineService {

    private final EngineRepository engineRepository;

    public HbmEngineService(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    @Override
    public Collection<Engine> findAll() {
        return engineRepository.findAll();
    }
}
