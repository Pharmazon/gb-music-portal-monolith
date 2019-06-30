package ru.geekbrains.musicportal.service.track;

import org.springframework.data.domain.Page;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.pojo.SpecFeature;
import ru.geekbrains.musicportal.service.common.CommonService;

import java.util.List;

public interface TrackService extends CommonService<Track> {

    Page<Track> getTrackWithPagingAndFiltering(int pageNumber, int pageSize, List<SpecFeature> specFeatures);

}
