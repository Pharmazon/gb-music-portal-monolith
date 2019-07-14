package ru.geekbrains.musicportal.service.playlist;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface PlaylistService extends CommonService<Playlist, PlaylistDto> {

    Page<Playlist> getPlaylistsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Playlist> specification);
}
