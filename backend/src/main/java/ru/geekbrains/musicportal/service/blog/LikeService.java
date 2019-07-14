package ru.geekbrains.musicportal.service.blog;

import ru.geekbrains.musicportal.entity.blog.Like;

import java.util.Optional;

public interface LikeService {

    Like save(Like entity);

    Optional<Like> findById(Long id);

    void deleteById(Long id);
}
