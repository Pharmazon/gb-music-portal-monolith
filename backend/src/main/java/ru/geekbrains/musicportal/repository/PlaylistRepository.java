package ru.geekbrains.musicportal.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.database.Playlist;

@Repository
public interface PlaylistRepository {
    Playlist getById(Long id);
}
