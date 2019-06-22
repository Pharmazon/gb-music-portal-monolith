package ru.geekbrains.musicportal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.geekbrains.musicportal.entity.CarEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CarDto extends AbstractDto {

    @NonNull
    private String mark;

    @Nullable
    private int power;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime addedDateTime;

    @NonNull
    private String engineId;

    public CarDto(@NotNull final CarEntity car) {
        super.setId(car.getId());
        super.setName(car.getName());
        this.power = car.getPower();
        this.engineId = car.getEngine().getId();
        this.addedDateTime = car.getAddedDateTime();
    }

}
