package ru.geekbrains.musicportal.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.blog.LikeDto;
import ru.geekbrains.musicportal.entity.blog.Like;
import ru.geekbrains.musicportal.repository.LikeRepository;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
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

    public Iterable<Like> findAll() {
        return likeRepository.findAll();
    }

    public Like save(LikeDto likeDto) {
        Like like = new Like();
        like.setId(likeDto.getId());
        like.setUser(like.getUser());
        like.setLikedEntity(likeDto.getEntityId());
        like.setTypeLikedEntity(likeDto.getEntity());
        return likeRepository.save(like);
    }
}