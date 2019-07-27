package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.like.LikeDto;
import ru.geekbrains.musicportal.entity.like.Like;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long>, CommonRepository<LikeDto> {

}
