package ru.job4j.cars.service;

import org.springframework.stereotype.*;
import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.*;

import java.util.*;

@Service
public class HbmPostService implements PostService {

    private final PostRepository postRepository;

    public HbmPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public boolean update(Post post) {
        return postRepository.update(post);
    }

    @Override
    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    @Override
    public Collection<Post> findForLastDay() {
        return postRepository.findForLastDay();
    }

    @Override
    public Collection<Post> findWithPhoto() {
        return postRepository.findWithPhoto();
    }

    @Override
    public Collection<Post> findOnCar(String name) {
        return postRepository.findOnCar(name);
    }

    @Override
    public Collection<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public boolean deleteById(int id) {
        return postRepository.deleteById(id);
    }
}
