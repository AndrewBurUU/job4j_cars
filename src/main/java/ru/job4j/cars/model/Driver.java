package ru.job4j.cars.model;

import javax.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;

    private String name;

    @Column(name = "user_id")
    private int userId;
}
