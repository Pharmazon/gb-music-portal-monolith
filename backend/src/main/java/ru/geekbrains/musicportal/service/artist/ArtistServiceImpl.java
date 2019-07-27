package ru.geekbrains.musicportal.service.artist;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.artist.ArtistDto;
import ru.geekbrains.musicportal.entity.album.Album;
import ru.geekbrains.musicportal.entity.artist.Artist;
import ru.geekbrains.musicportal.entity.track.AlbumTrack;
import ru.geekbrains.musicportal.repository.ArtistRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {

    private ArtistRepository artistRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository,
                             ModelMapper modelMapper) {
        this.artistRepository = artistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Artist saveOrUpdate(Artist entity) {
        return artistRepository.save(entity);
    }

    @Override
    public Optional<Artist> findOneEntityById(Long id) {
        return artistRepository.findById(id);
    }

    @Override
    public Artist convertToEntity(ArtistDto dto) {
        return modelMapper.map(dto, Artist.class);
    }

    @Override
    public boolean deleteById(Long id) {
        artistRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<ArtistDto> findAllDtos() {
        return artistRepository.findAllByIdNotNull();
    }

    @Override
    public Collection<Artist> findAll() {
        return (Collection<Artist>) artistRepository.findAll();
    }

    @Override
    public ArtistDto findOneDtoById(Long id) {
        return artistRepository.findOneById(id);
    }

    @Override
    public Page<Artist> getArtistsWithPagingAndFiltering(int pageNumber,
                                                         int pageSize,
                                                         Specification<Artist> specification) {
        return artistRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Collection<ArtistDto> findAllByGenreName(String name) {
        return artistRepository.findAllByGenreName(name);
    }

    @Override
    public Collection<ArtistDto> getTop(int max) {
        Collection<Artist> top = artistRepository.getTop(max);
        return top.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ArtistDto markOneAsDeleted(Long id) {
        Optional<Artist> optional = artistRepository.findById(id);
        if (!optional.isPresent()) return null;
        Artist artist = optional.get();
        artist.setIsDeleted(true);
        Collection<Album> albums = artist.getAlbums();
        for (Album album : albums) {
            album.setIsDeleted(true);
            Collection<AlbumTrack> albumTracks = album.getAlbumTracks();
            for (AlbumTrack albumTrack : albumTracks) {
                albumTrack.getTrack().setIsDeleted(true);
            }
        }
        saveOrUpdate(artist);
        return convertToDto(artist);
    }

    @Override
    public ArtistDto convertToDto(Artist entity) {
        return modelMapper.map(entity, ArtistDto.class);
    }

}
