package ru.geekbrains.musicportal.service.common;

import java.util.Collection;
import java.util.Optional;

public interface CommonService<E, D> {

    E saveOrUpdate(E entity);

    Optional<E> findOneEntityById(Long id);

    Collection<D> findAllDto();

    D findOneDtoById(Long id);

    E convertToEntity(D dto);
}
