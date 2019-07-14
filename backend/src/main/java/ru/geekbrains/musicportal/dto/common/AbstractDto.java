package ru.geekbrains.musicportal.dto.common;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.marker.CommonViews;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class AbstractDto implements Serializable {

    @JsonView(CommonViews.General.class)
    private Long id;

    @JsonView(CommonViews.General.class)
    private String name;

    @JsonView(CommonViews.General.class)
    private String description;

    @JsonView(CommonViews.All.class)
    private LocalDateTime lastUpdate;

    @JsonView(CommonViews.All.class)
    private LocalDateTime creationDate;
}
