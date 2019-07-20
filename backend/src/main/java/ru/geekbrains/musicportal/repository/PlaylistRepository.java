package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.playlist.Playlist;

import java.util.Collection;

@Repository
public interface PlaylistRepository extends CommonRepository<PlaylistDto>, CrudRepository<Playlist, Long>,
        JpaSpecificationExecutor<Playlist> {

    // Запрашиваем топ треков по лайком с группировкой по идентификатору сущности и сортировкой по убыванию с лимитом
    @Query(value = "select app_likes.entity_id as like_id, count(app_likes.id) as lid, app_playlists.*" +
            "from app_playlists" +
            "right join app_likes on app_likes.entity_id = app_playlists.id" +
            "where app_likes.entity = 'PLAYLIST'" +
            "group by like_id, app_tracks.id" +
            "order by lid desc" +
            "limit :maxEntities",
            nativeQuery = true)
    Collection<Playlist> getTop(@Param("maxEntities") int maxEntities);
}
