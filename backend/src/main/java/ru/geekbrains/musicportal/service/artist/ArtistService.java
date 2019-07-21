package ru.geekbrains.musicportal.service.artist;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.dto.artist.ArtistDto;
import ru.geekbrains.musicportal.entity.artist.Artist;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface ArtistService extends CommonService<Artist, ArtistDto> {

    Page<Artist> getArtistsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Artist> specification);

}
