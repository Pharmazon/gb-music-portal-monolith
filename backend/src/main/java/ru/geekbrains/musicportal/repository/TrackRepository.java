package ru.geekbrains.musicportal.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.database.Track;

@Repository
public interface TrackRepository {
    Track findOneById(Long id);
}
