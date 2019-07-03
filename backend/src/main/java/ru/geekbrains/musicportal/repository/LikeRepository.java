package ru.geekbrains.musicportal.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.blog.Like;

import java.util.List;

@Repository
public interface LikeRepository extends CrudRepository<Like,Long> {

    List<Like> findLikesByLikedEntityEqualsAndTypeLikedEntity(Long likedEntity, String typeLikedEntity);

    // Запрашиваем топ лайков по типу и группировкой по идентификатору сущности
    @Query("select l.likedEntity,count(l.id) from Like l where l.typeLikedEntity=:typeLikedEntity group by l.likedEntity")
    List<Object[]> getTop15(@Param("typeLikedEntity") String typeLikedEntity, Pageable pageable);
}
