package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.playlist.Playlist;

import java.util.Collection;

@Repository
public interface PlaylistRepository extends CrudRepository<Playlist, Long> {

    Collection<PlaylistDto> findAllByIdNotNull();

    Collection<PlaylistDto> findAllByBandId(Long bandId);

    PlaylistDto findOneByIdAndBandId(Long id, Long bandId);

}
