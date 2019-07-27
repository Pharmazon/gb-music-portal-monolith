package ru.geekbrains.musicportal.service.track;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.service.common.CommonService;

import java.util.Collection;

public interface TrackService extends CommonService<Track, TrackDto> {

    Page<Track> getTracksWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Track> specification);

    Collection<TrackDto> getTop(int topMax);

    Collection<TrackDto> getAllByArtistId(Long id);

    Collection<TrackDto> findAllByGenreName(String name);

    TrackDto markOneAsDeleted(Long id);

}
