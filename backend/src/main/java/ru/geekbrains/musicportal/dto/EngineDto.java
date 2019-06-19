package ru.geekbrains.musicportal.dto;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.musicportal.entity.EngineEntity;

public class EngineDto extends AbstractDto {

    @NonNull
    private String number;

    public EngineDto(@NotNull final EngineEntity entity) {
        super.setId(entity.getId());
        super.setName(entity.getName());
        this.number = entity.getNumber();
    }
}
