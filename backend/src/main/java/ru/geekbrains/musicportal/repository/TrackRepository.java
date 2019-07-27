package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.track.Track;

import java.util.Collection;

@Repository
public interface TrackRepository extends CommonRepository<TrackDto>, PagingAndSortingRepository<Track, Long>,
        JpaSpecificationExecutor<Track> {

    // Запрашиваем топ треков по лайком с группировкой по идентификатору сущности и сортировкой по убыванию с лимитом
    @Query(value = "select app_likes.entity_id as like_id, count(app_likes.id) as lid, app_tracks.*" +
            "from app_tracks" +
            "right join app_likes on app_likes.entity_id = app_tracks.id" +
            "where app_likes.entity = 'TRACK'" +
            "group by like_id, app_tracks.id" +
            "order by lid desc" +
            "limit :maxEntities",
            nativeQuery = true)
    Collection<Track> getTop(@Param("maxEntities") int maxEntities);

    Collection<TrackDto> findAllByArtist_Id(Long id);

    @Query("select t from Track t inner join fetch t.genres g where g.name = :name")
    Collection<TrackDto> findAllByGenreName(@Param("name") String name);
}