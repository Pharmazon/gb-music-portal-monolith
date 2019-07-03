package ru.geekbrains.musicportal.service.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.TrackDto;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.enums.FilterJoinTypeEnum;
import ru.geekbrains.musicportal.pojo.SpecFeature;
import ru.geekbrains.musicportal.repository.CategoryRepository;
import ru.geekbrains.musicportal.repository.LikeRepository;
import ru.geekbrains.musicportal.repository.TrackRepository;
import ru.geekbrains.musicportal.util.TrackUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;
    private CategoryRepository categoryRepository;
    private LikeRepository likeRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository, CategoryRepository categoryRepository, LikeRepository likeRepository) {
        this.trackRepository = trackRepository;
        this.categoryRepository = categoryRepository;
        this.likeRepository = likeRepository;
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

    /**
     * Функция выдает Топ треков
     * @param topMax Максимальное количество треков в рейтинге
     * @return Коллекция объектов TrackDto
     */
    public List<TrackDto> getTopTracks(int topMax){
        return likeRepository.getTop15("TRACK", PageRequest.of(0, topMax))
                .stream()
                .sorted(Comparator.comparingLong(o -> (Long) o[1]))
                .map(objects -> new TrackDto(trackRepository.findById((Long)objects[0]).orElse(null), (Long)objects[1]))
                .collect(Collectors.toList());
    }
}
