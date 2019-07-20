package ru.geekbrains.musicportal.service.playlist;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.repository.PlaylistRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistRepository playlistRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository,
                               ModelMapper modelMapper) {
        this.playlistRepository = playlistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Playlist saveOrUpdate(Playlist entity) {
        return playlistRepository.save(entity);
    }

    @Override
    public Optional<Playlist> findOneEntityById(Long id) {
        return playlistRepository.findById(id);
    }

    @Override
    public Collection<PlaylistDto> findAllDto() {
        return playlistRepository.findAllByIdNotNull();
    }

    @Override
    public PlaylistDto findOneDtoById(Long id) {
        return playlistRepository.findOneById(id);
    }

    @Override
    public Playlist convertToEntity(PlaylistDto dto) {
        return modelMapper.map(dto, Playlist.class);
    }

    @Override
    public void deleteById(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public Page<Playlist> getPlaylistsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Playlist> specification) {
        return playlistRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Collection<PlaylistDto> getTop(int max) {
        Collection<Playlist> top = playlistRepository.getTop(max);
        return top.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlaylistDto convertToDto(Playlist entity) {
        return modelMapper.map(entity, PlaylistDto.class);
    }
}
