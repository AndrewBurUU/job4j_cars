package ru.job4j.cars.repository;

import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import org.junit.jupiter.api.*;
import ru.job4j.cars.model.*;

import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HbmPostRepositoryTest {

    private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private static SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static CrudRepository crudRepository;

    private static HbmPostRepository hbmPostRepository;

    private static UserRepository userRepository;

    private static HbmEngineRepository hbmEngineRepository;

    private static HbmCarRepository hbmCarRepository;

    private static HbmPriceHistoryRepository hbmPriceHistoryRepository;

    private static HbmParticipateRepository hbmParticipateRepository;

    private static HbmFileRepository hbmFileRepository;

    private static HbmDriverRepository hbmDriverRepository;

    private static HbmHistoryOwnerRepository hbmHistoryOwnerRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        crudRepository = new CrudRepository(sf);
        hbmPostRepository = new HbmPostRepository(crudRepository);
        userRepository = new UserRepository(crudRepository);
        hbmEngineRepository = new HbmEngineRepository(crudRepository);
        hbmCarRepository = new HbmCarRepository(crudRepository);
        hbmPriceHistoryRepository = new HbmPriceHistoryRepository(crudRepository);
        hbmParticipateRepository = new HbmParticipateRepository(crudRepository);
        hbmFileRepository = new HbmFileRepository(crudRepository);
        hbmDriverRepository = new HbmDriverRepository(crudRepository);
        hbmHistoryOwnerRepository = new HbmHistoryOwnerRepository(crudRepository);

        var files = hbmFileRepository.findAll();
        for (var file : files) {
            hbmFileRepository.deleteById(file.getId());
        }

        var participates = hbmParticipateRepository.findAll();
        for (var participate : participates) {
            hbmParticipateRepository.deleteById(participate.getId());
        }

        var priceHistories = hbmPriceHistoryRepository.findAll();
        for (var priceHistory : priceHistories) {
            hbmPriceHistoryRepository.deleteById(priceHistory.getId());
        }

        var posts = hbmPostRepository.findAll();
        for (var post : posts) {
            hbmPostRepository.deleteById(post.getId());
        }

    }

    @AfterEach
    public void clearPosts() {
        var files = hbmFileRepository.findAll();
        for (var file : files) {
            hbmFileRepository.deleteById(file.getId());
        }

        var participates = hbmParticipateRepository.findAll();
        for (var participate : participates) {
            hbmParticipateRepository.deleteById(participate.getId());
        }

        var priceHistories = hbmPriceHistoryRepository.findAll();
        for (var priceHistory : priceHistories) {
            hbmPriceHistoryRepository.deleteById(priceHistory.getId());
        }

        var posts = hbmPostRepository.findAll();
        for (var post : posts) {
            hbmPostRepository.deleteById(post.getId());
        }
    }

    @Test
    public void whenFirstFindAllThenGetNothing() {
        var res = hbmPostRepository.findAll();
        assertThat(res.size()).isEqualTo(0);
    }

    @Test
    public void whenSaveThenGetSame() {
        var creationDate = now().truncatedTo(ChronoUnit.MINUTES);
        var user = new User();
        user.setLogin("user1");
        user.setPassword("1");
        user = userRepository.create(user);

        var engine = new Engine();
        engine.setName("engine1");
        engine = hbmEngineRepository.save(engine);

        var car = new Car();
        car.setName("car1");
        car.setEngine(engine);
        car = hbmCarRepository.save(car);

        var post = new Post();
        post.setDescription("post1");
        post.setCreated(creationDate);
        post.setUser(user);
        post.setCar(car);
        post = hbmPostRepository.save(post);

        var file = new File();
        file.setName("file1");
        file.setPath("files/BMWBack.jpg");
        file.setPost(post);
        file = hbmFileRepository.save(file);

        post.setFiles(List.of(file));

        var savedPost = hbmPostRepository.findById(post.getId()).get();
        assertThat(savedPost).usingRecursiveComparison().isEqualTo(post);
    }

    @Test
    public void whenFindByIncorrectIdThenGetEmpty() {
        var creationDate = now().truncatedTo(ChronoUnit.MINUTES);
        var user = new User();
        user.setLogin("user1");
        user.setPassword("1");
        user = userRepository.create(user);

        var engine = new Engine();
        engine.setName("engine1");
        engine = hbmEngineRepository.save(engine);

        var car = new Car();
        car.setName("car1");
        car.setEngine(engine);
        car = hbmCarRepository.save(car);

        var post = new Post();
        post.setDescription("post1");
        post.setCreated(creationDate);
        post.setUser(user);
        post.setCar(car);
        post = hbmPostRepository.save(post);
        var savedPost = hbmPostRepository.findById(2);
        assertThat(savedPost).isEqualTo(Optional.empty());
    }

    @Test
    public void whenFindForLastDayThenGetSame() {
        var creationDate = now().truncatedTo(ChronoUnit.MINUTES).minusDays(1);
        var user = new User();
        user.setLogin("user1");
        user.setPassword("1");
        user = userRepository.create(user);

        var engine = new Engine();
        engine.setName("engine1");
        engine = hbmEngineRepository.save(engine);

        var car = new Car();
        car.setName("car1");
        car.setEngine(engine);
        car = hbmCarRepository.save(car);

        var post = new Post();
        post.setDescription("post1");
        post.setCreated(creationDate);
        post.setUser(user);
        post.setCar(car);
        post = hbmPostRepository.save(post);
        var savedPost = hbmPostRepository.findForLastDay();
        assertThat(savedPost).isEqualTo(List.of(post));
    }

    @Test
    public void whenFindWithPhotoThenGetSame() {
        var creationDate = now().truncatedTo(ChronoUnit.MINUTES).minusDays(1);
        var user = new User();
        user.setLogin("user1");
        user.setPassword("1");
        user = userRepository.create(user);

        var engine = new Engine();
        engine.setName("engine1");
        engine = hbmEngineRepository.save(engine);

        var car = new Car();
        car.setName("car1");
        car.setEngine(engine);
        car = hbmCarRepository.save(car);

        var post = new Post();
        post.setDescription("post1");
        post.setCreated(creationDate);
        post.setUser(user);
        post.setCar(car);
        post = hbmPostRepository.save(post);
        var savedPost = hbmPostRepository.findForLastDay();
        assertThat(savedPost).isEqualTo(List.of(post));
    }

    @Test
    public void whenFindCarThenGetSame() {
        var creationDate = now().truncatedTo(ChronoUnit.MINUTES);
        var user = new User();
        user.setLogin("user1");
        user.setPassword("1");
        user = userRepository.create(user);

        var engine = new Engine();
        engine.setName("engine1");
        engine = hbmEngineRepository.save(engine);

        var car = new Car();
        car.setName("car1");
        car.setEngine(engine);
        car = hbmCarRepository.save(car);

        var post = new Post();
        post.setDescription("post1");
        post.setCreated(creationDate);
        post.setUser(user);
        post.setCar(car);
        post = hbmPostRepository.save(post);
        var findOnCar = hbmPostRepository.findOnCar(car.getName());
        assertThat(findOnCar).isEqualTo(List.of(post));
    }

    @Test
    public void whenFindAllThenGetAll() {
        var creationDate = now().truncatedTo(ChronoUnit.MINUTES);
        var user = new User();
        user.setLogin("user1");
        user.setPassword("1");
        user = userRepository.create(user);

        var engine = new Engine();
        engine.setName("engine1");
        engine = hbmEngineRepository.save(engine);

        var car = new Car();
        car.setName("car1");
        car.setEngine(engine);
        car = hbmCarRepository.save(car);

        var post = new Post();
        post.setDescription("post1");
        post.setCreated(creationDate);
        post.setUser(user);
        post.setCar(car);
        post = hbmPostRepository.save(post);

        var file = new File();
        file.setName("file1");
        file.setPath("files/BMWBack.jpg");
        file.setPost(post);
        file = hbmFileRepository.save(file);

        post.setFiles(List.of(file));

        var savedPost = hbmPostRepository.findAll();
        assertThat(savedPost).isEqualTo(List.of(post));
    }

    @Test
    public void whenDeleteThenGetNothing() {
        var creationDate = now().truncatedTo(ChronoUnit.MINUTES);
        var user = new User();
        user.setLogin("user1");
        user.setPassword("1");
        user = userRepository.create(user);

        var engine = new Engine();
        engine.setName("engine1");
        engine = hbmEngineRepository.save(engine);

        var car = new Car();
        car.setName("car1");
        car.setEngine(engine);
        car = hbmCarRepository.save(car);

        var post = new Post();
        post.setDescription("post1");
        post.setCreated(creationDate);
        post.setUser(user);
        post.setCar(car);
        post = hbmPostRepository.save(post);

        var file = new File();
        file.setName("file1");
        file.setPath("files/BMWBack.jpg");
        file.setPost(post);
        file = hbmFileRepository.save(file);

        post.setFiles(List.of(file));

        hbmFileRepository.deleteById(file.getId());

        hbmPostRepository.deleteById(post.getId());

        var savedPost = hbmPostRepository.findById(post.getId());
        assertThat(savedPost).isEqualTo(Optional.empty());
    }

}