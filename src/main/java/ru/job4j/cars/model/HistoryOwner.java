package ru.job4j.cars.model;

import java.time.*;
import javax.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "history_owner")
public class HistoryOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;

    @Column(name = "car_id")
    private int carId;

    @Column(name = "driver_id")
    private int driverId;

    private LocalDateTime startAt = LocalDateTime.now();

    private LocalDateTime endAt = LocalDateTime.now();

}
