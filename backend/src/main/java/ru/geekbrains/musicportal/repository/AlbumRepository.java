package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.album.AlbumDto;
import ru.geekbrains.musicportal.entity.album.Album;

import java.util.Collection;

@Repository
public interface AlbumRepository extends CommonRepository<AlbumDto>, CrudRepository<Album, Long>,
        JpaSpecificationExecutor<Album> {

    // Запрашиваем топ треков по лайком с группировкой по идентификатору сущности и сортировкой по убыванию с лимитом
    @Query(value = "select app_likes.entity_id as like_id, count(app_likes.id) as lid, app_albums.*" +
            "from app_albums" +
            "right join app_likes on app_likes.entity_id = app_albums.id" +
            "where app_likes.entity = 'ALBUM'" +
            "group by like_id, app_tracks.id" +
            "order by lid desc" +
            "limit :maxEntities",
            nativeQuery = true)
    Collection<Album> getTop(@Param("maxEntities") int maxEntities);
}
