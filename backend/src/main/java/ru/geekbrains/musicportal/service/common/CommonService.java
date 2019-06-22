package ru.geekbrains.musicportal.service.common;

import ru.geekbrains.musicportal.entity.AbstractEntity;

import java.util.Optional;

public interface CommonService<T extends AbstractEntity> {

    T save(T entity);

    Optional<T> findById(String id);

}
