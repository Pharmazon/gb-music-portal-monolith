package ru.geekbrains.musicportal.service.common;

import java.util.Optional;

public interface CommonService<T> {

    T save(T entity);

    Optional<T> findById(Long id);

}
