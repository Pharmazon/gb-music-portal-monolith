package ru.geekbrains.musicportal.service.like;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.like.LikeDto;
import ru.geekbrains.musicportal.entity.like.Like;
import ru.geekbrains.musicportal.repository.LikeRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository,
                           ModelMapper modelMapper) {
        this.likeRepository = likeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Like saveOrUpdate(Like entity) {
        return likeRepository.save(entity);
    }

    @Override
    public Optional<Like> findOneEntityById(Long id) {
        return likeRepository.findById(id);
    }

    @Override
    public LikeDto findOneDtoById(Long id) {
        return likeRepository.findOneById(id);
    }

    @Override
    public Collection<LikeDto> findAllDtos() {
        return (Collection<LikeDto>) likeRepository.findAllByIdNotNull();
    }

    @Override
    public Collection<Like> findAll() {
        return (Collection<Like>) likeRepository.findAll();
    }

    @Override
    public Like convertToEntity(LikeDto dto) {
        return modelMapper.map(dto, Like.class);
    }

    @Override
    public LikeDto convertToDto(Like entity) {
        return modelMapper.map(entity, LikeDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        likeRepository.deleteById(id);
        return true;
    }
}
