package ru.job4j.cars.model;

import javax.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;

    private String name;

    @Include
    private String path;

}
