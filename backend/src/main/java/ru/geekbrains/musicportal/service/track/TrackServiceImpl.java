package ru.geekbrains.musicportal.service.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.enums.FilterJoinTypeEnum;
import ru.geekbrains.musicportal.entity.common.SpecFeature;
import ru.geekbrains.musicportal.entity.track.TrackSpec;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.repository.CategoryRepository;
import ru.geekbrains.musicportal.repository.TrackRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository,CategoryRepository categoryRepository) {
        this.trackRepository = trackRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Track save(Track entity) {
        return trackRepository.save(entity);
    }

    @Override
    public Optional<Track> findById(Long id) {
        return trackRepository.findById(id);
    }

    public Page<Track> getTrackWithPagingAndFiltering(int pageNumber, int pageSize, List<SpecFeature> specFeatures) {
        Specification<Track> trackSpecification = Specification.where(null);
        for (SpecFeature specFeature: specFeatures) {
            if (specFeature.getJoinType() == FilterJoinTypeEnum.AND){
                trackSpecification = trackSpecification.and(TrackSpec.getSpecification(specFeature));
            }else if (specFeature.getJoinType() == FilterJoinTypeEnum.OR){
                trackSpecification = trackSpecification.or(TrackSpec.getSpecification(specFeature));
            }else{
                trackSpecification = trackSpecification.and(TrackSpec.getSpecification(specFeature));
            }
        }
        return trackRepository.findAll(trackSpecification, PageRequest.of(pageNumber, pageSize));
    }
}
