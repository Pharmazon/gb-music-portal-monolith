package ru.geekbrains.musicportal.service.track;

import org.springframework.data.domain.Page;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.pojo.SpecFeature;
import ru.geekbrains.musicportal.service.common.CommonService;

import java.util.List;

public interface TrackService extends CommonService<Track, TrackDto> {

    Page<Track> getTrackWithPagingAndFiltering(int pageNumber, int pageSize, List<SpecFeature> specFeatures);

    void deleteById(Long id);

//    Collection<TrackDto> findAllByPlaylistIdAndBandId(Long playlistId, Long bandId);

//    TrackDto findOneByIdAndPlaylistTrackIdAndBandId(Long id, Long playlistId, Long bandId);
    List<TrackDto> getTopTracks(int topMax);
}
