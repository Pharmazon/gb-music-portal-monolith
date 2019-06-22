package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.Track;

@Repository
public interface TrackRepository extends CrudRepository<Track, Long> {
    Track findOneById(Long id);
}
