package ru.geekbrains.musicportal.service.playlist;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.repository.PlaylistRepository;

import java.util.Collection;
import java.util.Optional;

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
    public Playlist save(Playlist entity) {
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

}
