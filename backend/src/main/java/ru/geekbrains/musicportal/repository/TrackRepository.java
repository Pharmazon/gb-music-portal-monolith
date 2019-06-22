package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.track.Track;

@Repository
public interface TrackRepository extends CrudRepository<Track, String> {


}
