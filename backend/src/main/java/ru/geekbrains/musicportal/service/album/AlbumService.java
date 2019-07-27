package ru.geekbrains.musicportal.service.album;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.dto.album.AlbumDto;
import ru.geekbrains.musicportal.entity.album.Album;
import ru.geekbrains.musicportal.service.common.CommonService;

import java.util.Collection;

public interface AlbumService extends CommonService<Album, AlbumDto> {

    Page<Album> getAlbumsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Album> specification);

    Collection<AlbumDto> getTop(int max);

    Collection<AlbumDto> getAllByArtistId(Long id);

    Collection<AlbumDto> findAllByGenreName(String name);
}
