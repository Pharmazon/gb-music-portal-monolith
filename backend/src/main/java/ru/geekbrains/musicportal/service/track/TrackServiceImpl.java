package ru.geekbrains.musicportal.service.track;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.enums.FilterJoinTypeEnum;
import ru.geekbrains.musicportal.pojo.SpecFeature;
import ru.geekbrains.musicportal.repository.TrackRepository;
import ru.geekbrains.musicportal.util.TrackUtil;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    public Track save(Track entity) {
        return trackRepository.save(entity);
    }

    @Override
    public Optional<Track> findById(Long id) {
        return trackRepository.findById(id);
    }

    @Override
    public Collection<TrackDto> findAll() {
        return null;
    }

    public Page<Track> getTrackWithPagingAndFiltering(int pageNumber, int pageSize, List<SpecFeature> specFeatures) {
        Specification<Track> trackSpecification = Specification.where(null);
        for (SpecFeature specFeature : specFeatures) {
            if (specFeature.getJoinType() == FilterJoinTypeEnum.AND) {
                trackSpecification = trackSpecification.and(TrackUtil.getSpecification(specFeature));
            } else if (specFeature.getJoinType() == FilterJoinTypeEnum.OR) {
                trackSpecification = trackSpecification.or(TrackUtil.getSpecification(specFeature));
            } else {
                trackSpecification = trackSpecification.and(TrackUtil.getSpecification(specFeature));
            }
        }
        return trackRepository.findAll(trackSpecification, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public void deleteById(Long id) {
        trackRepository.deleteById(id);
    }

//    @Override
//    public Collection<TrackDto> findAllByPlaylistIdAndBandId(Long playlistId, Long bandId) {
//        return trackRepository.findAllByPlaylistTrackIdAndBandId(playlistId, bandId);
//    }
//
//    @Override
//    public TrackDto findOneByIdAndPlaylistTrackIdAndBandId(Long id, Long playlistId, Long bandId) {
//        return trackRepository.findOneByIdAndPlaylistTrackIdAndBandId(id, playlistId, bandId);
//    }

    @Override
    public Track convertToEntity(TrackDto dto) {
        return modelMapper.map(dto, Track.class);
    }
}
