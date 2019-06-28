package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.track.Track;

@Repository
public interface TrackRepository extends PagingAndSortingRepository<Track, Long>, JpaSpecificationExecutor<Track> {

}
