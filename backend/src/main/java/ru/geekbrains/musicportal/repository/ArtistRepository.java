package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.artist.ArtistDto;
import ru.geekbrains.musicportal.entity.artist.Artist;

import java.util.Collection;

@Repository
public interface ArtistRepository extends CommonRepository<ArtistDto>, CrudRepository<Artist, Long>,
        JpaSpecificationExecutor<Artist> {

    @Query(value = "select app_likes.entity_id as like_id, count(app_likes.id) as lid, app_artists.*" +
            "from app_artists" +
            "right join app_likes on app_likes.entity_id = app_artists.id" +
            "where app_likes.entity = 'ARTIST'" +
            "group by like_id, app_artists.id" +
            "order by lid desc" +
            "limit :maxEntities",
            nativeQuery = true)
    Collection<Artist> getTop(@Param("maxEntities") int maxEntities);

    @Query("select a from Artist a inner join fetch a.genres g where g.name = :name")
    Collection<ArtistDto> findAllByGenreName(@Param("name") String name);
}
