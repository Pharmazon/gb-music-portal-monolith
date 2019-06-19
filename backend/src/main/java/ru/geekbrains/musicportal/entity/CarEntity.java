package ru.geekbrains.musicportal.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.musicportal.dto.CarDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mp_cars")
public class CarEntity extends AbstractEntity {

    @Column(name = "mark", nullable = false)
    private String mark;

    @Column(name = "power", nullable = false)
    private int power;

    @Column(name = "added_date", nullable = false, updatable = false)
    private LocalDateTime addedDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id")
    private EngineEntity engine;

    public static CarDto convertToDto(CarEntity car) {
        CarDto dto = new CarDto();
        dto.setMark(car.getMark());
        dto.setEngineId(car.getEngine().getId());
        dto.setPower(car.getPower());
        return dto;
    }
}
