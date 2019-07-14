package ru.geekbrains.musicportal.dto.track;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.track.Genre;
import ru.geekbrains.musicportal.marker.CommonViews;

@Data
@NoArgsConstructor
public class GenreDto {

    @JsonView(CommonViews.General.class)
    private Long id;

    @JsonView(CommonViews.General.class)
    private String name;

    public GenreDto(Genre genre) {
        if (genre == null) return;
        id = genre.getId();
        name = genre.getName();
    }

}
