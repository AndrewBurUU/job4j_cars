package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import lombok.EqualsAndHashCode.Include;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "participates")
public class Participate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;

    @Column(name = "post_id")
    private int postId;

    @Column(name = "user_id")
    private int userId;

}
