package ru.geekbrains.musicportal.service.common;

import java.util.Collection;
import java.util.Optional;

public interface CommonService<E, D> {

    E saveOrUpdate(E entity);

    Optional<E> findOneEntityById(Long id);

    D findOneDtoById(Long id);

    Collection<D> findAllDtos();

    Collection<E> findAll();

    E convertToEntity(D dto);

    D convertToDto(E entity);

    boolean deleteById(Long id);
}
