package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.band.BandDto;
import ru.geekbrains.musicportal.entity.band.Band;

import java.util.Collection;

@Repository
public interface BandRepository extends CrudRepository<Band, Long> {

    Collection<BandDto> findAllByNameNotNull();
}
