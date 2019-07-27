package ru.geekbrains.musicportal.service.playlist;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.service.common.CommonService;

import java.util.Collection;

public interface PlaylistService extends CommonService<Playlist, PlaylistDto> {

    Page<Playlist> getPlaylistsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Playlist> specification);

    Collection<PlaylistDto> getTop(int max);

    Collection<PlaylistDto> getAllByUserId(Long id);

    Collection<PlaylistDto> findAllByGenreName(String name);

    PlaylistDto addTrackToPlaylist(Long playlistId, Long trackId);

    PlaylistDto deleteTrackFromPlaylist(Long playlistId, Long trackId);

//    PlaylistDto changeTrackPositionInPlaylist(Long playlistId, Long trackId, Integer newPosition);

}
