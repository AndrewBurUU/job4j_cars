package ru.job4j.cars.service;

import org.junit.jupiter.api.*;
import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.*;

import java.time.temporal.*;
import java.util.*;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class HbmPostServiceTest {

    private HbmPostService hbmPostService;

    private PostRepository postRepository;

    @BeforeEach
    public void initService() {
        postRepository = mock(PostRepository.class);
        hbmPostService = new HbmPostService(postRepository);
    }

    @Test
    public void whenCreateNewOneThenSavePost() {
        var creationDate = now().truncatedTo(ChronoUnit.HOURS);
        var post = new Post(1, "post1", creationDate, new User(), new Car(), List.of());
        when(postRepository.save(post)).thenReturn(post);

        var result = hbmPostService.save(post);
        assertThat(result).isEqualTo(post);
    }

    @Test
    public void whenFindByIdThenGetPost() {
        var creationDate = now().truncatedTo(ChronoUnit.HOURS);
        var post = new Post(1, "post1", creationDate, new User(), new Car(), List.of());
        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));

        var result = hbmPostService.findById(post.getId()).get();
        assertThat(result).isEqualTo(post);
    }

    @Test
    public void whenFindLastDayThenGetList() {
        var creationDate = now().truncatedTo(ChronoUnit.HOURS);
        var post = new Post(1, "post1", creationDate, new User(), new Car(), List.of());
        var postYesterday1 = new Post(2, "post2", creationDate.minusDays(1), new User(), new Car(), List.of());
        var postYesterday2 = new Post(3, "post3", creationDate.minusDays(1), new User(), new Car(), List.of());
        var postList = List.of(postYesterday1, postYesterday2);
        when(postRepository.findForLastDay()).thenReturn(postList);

        var result = hbmPostService.findForLastDay();
        assertThat(result).isEqualTo(postList);
    }

    @Test
    public void whenNoLastDayThenEmptyList() {
        var creationDate = now().truncatedTo(ChronoUnit.HOURS);
        var post = new Post(1, "post1", creationDate, new User(), new Car(), List.of());
        when(postRepository.findForLastDay()).thenReturn(List.of());

        var result = hbmPostService.findForLastDay();
        assertThat(result).isEqualTo(List.of());
    }

    @Test
    public void whenFindWithPhotoThenGetList() {
        var creationDate = now().truncatedTo(ChronoUnit.HOURS);
        var post = new Post(1, "post1", creationDate, new User(), new Car(), List.of(new File()));
        when(postRepository.findWithPhoto()).thenReturn(List.of(post));

        var result = hbmPostService.findWithPhoto();
        assertThat(result).isEqualTo(List.of(post));
    }

    @Test
    public void whenNoPhotoThenEmptyList() {
        var creationDate = now().truncatedTo(ChronoUnit.HOURS);
        var post = new Post(1, "post1", creationDate, new User(), new Car(), List.of());
        when(postRepository.findWithPhoto()).thenReturn(List.of());

        var result = hbmPostService.findWithPhoto();
        assertThat(result).isEqualTo(List.of());
    }

    @Test
    public void whenFindCarThenGetList() {
        var creationDate = now().truncatedTo(ChronoUnit.HOURS);
        var carName = "Mercedes";
        var car = new Car(1, carName, new Engine());
        var post = new Post(1, "post1", creationDate, new User(), car, List.of());
        when(postRepository.findOnCar(carName)).thenReturn(List.of(post));

        var result = hbmPostService.findOnCar(carName);
        assertThat(result).isEqualTo(List.of(post));
    }

    @Test
    public void whenNoCarThenEmptyList() {
        var creationDate = now().truncatedTo(ChronoUnit.HOURS);
        var carName = "Mercedes";
        var post = new Post(1, "post1", creationDate, new User(), new Car(), List.of());
        when(postRepository.findOnCar(carName)).thenReturn(List.of());

        var result = hbmPostService.findOnCar(carName);
        assertThat(result).isEqualTo(List.of());
    }

    @Test
    public void whenFindAllThenGetList() {
        var creationDate = now().truncatedTo(ChronoUnit.HOURS);
        var post = new Post(1, "post1", creationDate, new User(), new Car(), List.of());
        when(postRepository.findAll()).thenReturn(List.of(post));

        var result = hbmPostService.findAll();
        assertThat(result).isEqualTo(List.of(post));
    }

    @Test
    public void whenDeleteThenTrue() {
        var creationDate = now().truncatedTo(ChronoUnit.HOURS);
        var post = new Post(1, "post1", creationDate, new User(), new Car(), List.of());
        when(postRepository.deleteById(post.getId())).thenReturn(true);

        var result = hbmPostService.deleteById(post.getId());
        assertThat(result).isTrue();
    }

    @Test
    public void whenNothingDeleteThenFalse() {
        var result = hbmPostService.deleteById(0);
        assertThat(result).isFalse();
    }

}