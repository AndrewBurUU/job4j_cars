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

@Disabled
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
    public void clearTasks() {
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
    public void whenSaveThenGetSame() {
        var creationDate = now().truncatedTo(ChronoUnit.MINUTES);
        var user = new User(1, "user1", "1");
        var driver = new Driver(1, "driver1", user);
        var engine = new Engine(1, "engine1");
        var car = new Car(1, "car1", engine, Set.of(driver));

        var priceHistory = new PriceHistory(1, 1, 2, creationDate, new Post());
        var participate = new Participate(1, new Post(), user);
        var file = new File(1, "file1", "files/BMWBack.jpg", new Post());
        var post = new Post(1, "post1", creationDate,
                user,
                car,
                List.of(priceHistory),
                List.of(user),
                List.of(file));
        post = hbmPostRepository.save(post);
        var savedPost = hbmPostRepository.findById(post.getId()).get();
        assertThat(savedPost).usingRecursiveComparison().isEqualTo(post);
    }

}