package ru.geekbrains.musicportal.service.common;

import java.util.Collection;
import java.util.Optional;

public interface CommonService<ENTITY, DTO> {

    ENTITY saveOrUpdate(ENTITY entity);

    Optional<ENTITY> findOneEntityById(Long id);

    Collection<DTO> findAllDto();

    DTO findOneDtoById(Long id);

    ENTITY convertToEntity(DTO dto);
}
