package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.playlist.Playlist;

@Repository
public interface PlaylistRepository extends CommonRepository<PlaylistDto>, CrudRepository<Playlist, Long> {

}
