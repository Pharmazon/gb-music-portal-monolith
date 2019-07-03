package ru.geekbrains.musicportal.service.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.entity.blog.Like;
import ru.geekbrains.musicportal.dto.TrackDto;
import ru.geekbrains.musicportal.repository.LikeRepository;
import ru.geekbrains.musicportal.repository.TrackRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final TrackRepository trackRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, TrackRepository trackRepository) {
        this.likeRepository = likeRepository;
        this.trackRepository = trackRepository;
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
