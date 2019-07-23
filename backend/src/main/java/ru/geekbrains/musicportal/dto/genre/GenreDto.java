package ru.geekbrains.musicportal.dto.genre;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.genre.Genre;
import ru.geekbrains.musicportal.marker.GenreViews;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class GenreDto implements Serializable {

    @JsonView(GenreViews.All.class)
    private Long id;

    @JsonView(GenreViews.All.class)
    private String name;

    public GenreDto(Genre genre) {
        if (genre == null) return;
        id = genre.getId();
        name = genre.getName();
    }

}
