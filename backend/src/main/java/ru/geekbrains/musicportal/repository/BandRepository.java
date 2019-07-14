package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.band.BandDto;
import ru.geekbrains.musicportal.entity.band.Band;

@Repository
public interface BandRepository extends CommonRepository<BandDto>, CrudRepository<Band, Long>,
        JpaSpecificationExecutor<Band> {

}
