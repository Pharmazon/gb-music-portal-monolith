package ru.geekbrains.musicportal.service.track;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.repository.TrackRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository,
                            ModelMapper modelMapper) {
        this.trackRepository = trackRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Track saveOrUpdate(Track entity) {
        return trackRepository.save(entity);
    }

    @Override
    public Optional<Track> findOneEntityById(Long id) {
        return trackRepository.findById(id);
    }

    @Override
    public Track convertToEntity(TrackDto dto) {
        return modelMapper.map(dto, Track.class);
    }

    /**
     * Функция выдает Топ треков
     * @param topMax Максимальное количество треков в рейтинге
     * @return Коллекция объектов TrackDto
     */
    public Collection<TrackDto> getTop(int topMax){
        Collection<Track> topTracks = trackRepository.getTop(topMax);
        return topTracks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TrackDto> getAllByArtistId(Long id) {
        return trackRepository.findAllByArtist_Id(id);
    }

    @Override
    public Collection<TrackDto> findAllByGenreName(String genreName) {
        return trackRepository.findAllByGenreName(genreName);
    }

    @Override
    public TrackDto markOneAsDeleted(Long id) {
        return trackRepository.markOneAsDeleted(id);
    }

    @Override
    public TrackDto convertToDto(Track entity) {
        return modelMapper.map(entity, TrackDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        trackRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<TrackDto> findAllDtos() {
        return trackRepository.findAllByIdNotNull();
    }

    @Override
    public Collection<Track> findAll() {
        return (Collection<Track>) trackRepository.findAll();
    }

    @Override
    public TrackDto findOneDtoById(Long id) {
        return trackRepository.findOneById(id);
    }

    public Page<Track> getTracksWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Track> specification) {
        return trackRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));
    }
}
