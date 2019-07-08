package ru.geekbrains.musicportal.repository;

import java.util.Collection;

public interface CommonRepository<DTO> {

    DTO findOneById(Long id);

    Collection<DTO> findAllByIdNotNull();
}
