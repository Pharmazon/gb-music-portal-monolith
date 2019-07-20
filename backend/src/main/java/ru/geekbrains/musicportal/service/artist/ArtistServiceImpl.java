package ru.geekbrains.musicportal.service.artist;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.artist.ArtistProfileDto;
import ru.geekbrains.musicportal.entity.artist.Artist;
import ru.geekbrains.musicportal.repository.ArtistRepository;
import ru.geekbrains.musicportal.repository.TrackRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private ArtistRepository artistRepository;
    private TrackRepository trackRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository,
                             TrackRepository trackRepository,
                             ModelMapper modelMapper) {
        this.artistRepository = artistRepository;
        this.modelMapper = modelMapper;
        this.trackRepository = trackRepository;
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
    public Artist convertToEntity(ArtistProfileDto dto) {
        return modelMapper.map(dto, Artist.class);
    }

    @Override
    public void deleteById(Long id) {
        artistRepository.deleteById(id);
    }

    @Override
    public Collection<ArtistProfileDto> findAllDto() {
        return artistRepository.findAllByIdNotNull();
    }

    @Override
    public ArtistProfileDto findOneDtoById(Long id) {
        return artistRepository.findOneById(id);
    }

    @Override
    public Page<Artist> getArtistsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Artist> specification) {
        return artistRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public ArtistProfileDto convertToDto(Artist entity) {
        return modelMapper.map(entity, ArtistProfileDto.class);
    }
}
