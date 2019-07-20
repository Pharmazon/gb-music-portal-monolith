package ru.geekbrains.musicportal.service.track;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.service.common.CommonService;
import ru.geekbrains.musicportal.specification.SpecFeature;

import java.util.Collection;
import java.util.List;

public interface TrackService extends CommonService<Track, TrackDto> {

    Page<Track> getTrackWithPagingAndFiltering(int pageNumber, int pageSize, List<SpecFeature> specFeatures);

    Page<Track> getTracksWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Track> specification);

    Collection<TrackDto> getTopTracks(int topMax);

    Track findTrackById(Long id);

}
