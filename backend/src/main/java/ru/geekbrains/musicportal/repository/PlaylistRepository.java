package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.Playlist;

@Repository
public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
    Playlist findOneById(Long id);
}
