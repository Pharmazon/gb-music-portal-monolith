package ru.geekbrains.musicportal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public abstract class AbstractDto implements Serializable {

    private String id;

    private String name;

    private String description;
}
