package ru.geekbrains.musicportal.service.album;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.musicportal.dto.album.AlbumDto;
import ru.geekbrains.musicportal.entity.album.Album;
import ru.geekbrains.musicportal.entity.track.AlbumTrack;
import ru.geekbrains.musicportal.repository.AlbumRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumRepository albumRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository,
                            ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Album saveOrUpdate(Album entity) {
        return albumRepository.save(entity);
    }

    @Override
    public Optional<Album> findOneEntityById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public Collection<AlbumDto> findAllDtos() {
        return albumRepository.findAllByIdNotNull();
    }

    @Override
    public Collection<Album> findAll() {
        return (Collection<Album>) albumRepository.findAll();
    }

    @Override
    public AlbumDto findOneDtoById(Long id) {
        return albumRepository.findOneById(id);
    }

    @Override
    public Album convertToEntity(AlbumDto dto) {
        return modelMapper.map(dto, Album.class);
    }

    @Override
    public boolean deleteById(Long id) {
        albumRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<Album> getAlbumsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Album> specification) {
        return albumRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Collection<AlbumDto> getTop(int max) {
        Collection<Album> top = albumRepository.getTop(max);
        return top.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<AlbumDto> getAllByArtistId(Long id) {
        return albumRepository.getAllByArtist_Id(id);
    }

    @Override
    public Collection<AlbumDto> findAllByGenreName(String name) {
        return albumRepository.findAllByGenreName(name);
    }

    @Transactional
    @Override
    public AlbumDto markOneAsDeleted(Long id) {
        Optional<Album> optional = albumRepository.findById(id);
        if (!optional.isPresent()) return null;
        Album album = optional.get();
        album.setIsDeleted(true);
        Collection<AlbumTrack> albumTracks = album.getAlbumTracks();
        for (AlbumTrack albumTrack : albumTracks) {
            albumTrack.getTrack().setIsDeleted(true);
        }
        saveOrUpdate(album);
        return convertToDto(album);
    }

    @Override
    public AlbumDto convertToDto(Album entity) {
        return modelMapper.map(entity, AlbumDto.class);
    }
}
