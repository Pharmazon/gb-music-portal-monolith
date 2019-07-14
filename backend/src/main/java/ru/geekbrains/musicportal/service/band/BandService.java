package ru.geekbrains.musicportal.service.band;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.dto.band.BandDto;
import ru.geekbrains.musicportal.entity.band.Band;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface BandService extends CommonService<Band, BandDto> {

    Page<Band> getBandsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Band> specification);
}
