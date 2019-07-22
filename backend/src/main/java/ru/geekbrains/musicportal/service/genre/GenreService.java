package ru.geekbrains.musicportal.service.genre;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.dto.genre.GenreDto;
import ru.geekbrains.musicportal.entity.genre.Genre;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface GenreService extends CommonService<Genre, GenreDto> {

    Page<Genre> getGenresWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Genre> specification);
}
