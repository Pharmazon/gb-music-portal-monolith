package ru.geekbrains.musicportal.service.common;

import ru.geekbrains.musicportal.entity.common.AbstractEntity;

import java.util.Collection;
import java.util.Optional;

public interface CommonService<ENTITY extends AbstractEntity, DTO> {

    ENTITY save(ENTITY entity);

    Optional<ENTITY> findOneEntityById(Long id);

    Collection<DTO> findAllDto();

    DTO findOneDtoById(Long id);

    ENTITY convertToEntity(DTO dto);
}
