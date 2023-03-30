package ru.job4j.cars.service;

import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.*;

import java.util.*;

@Service
public class HbmFileService implements FileService {

    private final FileRepository fileRepository;

    public HbmFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }

    @Override
    public boolean update(File file) {
        return fileRepository.update(file);
    }

    @Override
    public Collection<File> findAll() {
        return fileRepository.findAll();
    }
}
