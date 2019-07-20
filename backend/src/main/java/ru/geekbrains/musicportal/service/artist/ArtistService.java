package ru.geekbrains.musicportal.service.artist;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.dto.artist.ArtistProfileDto;
import ru.geekbrains.musicportal.entity.artist.Artist;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface ArtistService extends CommonService<Artist, ArtistProfileDto> {

    Page<Artist> getArtistsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Artist> specification);

    ArtistProfileDto convertToDto(Artist entity);
}
