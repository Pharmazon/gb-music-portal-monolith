package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.blog.LikeDto;
import ru.geekbrains.musicportal.entity.blog.Like;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long>, CommonRepository<LikeDto> {

}
