package ru.geekbrains.musicportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.musicportal.entity.Like;
import ru.geekbrains.musicportal.repository.LikeRepository;

import java.util.Optional;

public class LikeServiceImpl implements LikeService {

    LikeRepository likeRepository;

    @Autowired
    public void setLikeRepository(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public Like save(Like entity) {
        return likeRepository.save(entity);
    }

    @Override
    public Optional<Like> findById(Long id) {
        return likeRepository.findById(id);
    }

    public void deleteById(Long id) {
        likeRepository.deleteById(id);
    }
}
