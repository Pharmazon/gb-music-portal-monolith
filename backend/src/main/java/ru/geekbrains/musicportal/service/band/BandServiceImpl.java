package ru.geekbrains.musicportal.service.band;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.band.BandDto;
import ru.geekbrains.musicportal.entity.band.Band;
import ru.geekbrains.musicportal.repository.BandRepository;
import ru.geekbrains.musicportal.repository.TrackRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class BandServiceImpl implements BandService {

    private BandRepository bandRepository;
    private TrackRepository trackRepository;
    private ModelMapper modelMapper;

    @Autowired
    public BandServiceImpl(BandRepository bandRepository,
                           TrackRepository trackRepository,
                           ModelMapper modelMapper) {
        this.bandRepository = bandRepository;
        this.modelMapper = modelMapper;
        this.trackRepository = trackRepository;
    }

    @Override
    public Band saveOrUpdate(Band entity) {
        return bandRepository.save(entity);
    }

    @Override
    public Optional<Band> findOneEntityById(Long id) {
        return bandRepository.findById(id);
    }

    @Override
    public Band convertToEntity(BandDto dto) {
        return modelMapper.map(dto, Band.class);
    }

    @Override
    public Collection<BandDto> findAllDto() {
        return bandRepository.findAllByIdNotNull();
    }

    @Override
    public BandDto findOneDtoById(Long id) {
        return bandRepository.findOneById(id);
    }

    @Override
    public Page<Band> getBandsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Band> specification) {
        return bandRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));
    }
}
