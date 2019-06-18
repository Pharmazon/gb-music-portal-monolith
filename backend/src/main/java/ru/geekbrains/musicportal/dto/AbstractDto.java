package ru.geekbrains.musicportal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractDto implements Serializable {

    @NotNull
    private String id;

    @Nullable
    private String name;
}
