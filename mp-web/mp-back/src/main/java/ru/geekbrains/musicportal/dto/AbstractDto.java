package ru.geekbrains.musicportal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class AbstractDto implements Serializable {

    private Long id;

    private String name;

    private String description;

    private LocalDateTime lastUpdate;

    private LocalDateTime creationDate;
}
