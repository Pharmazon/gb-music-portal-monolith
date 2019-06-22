package ru.geekbrains.musicportal.service;

import ru.geekbrains.musicportal.dto.AbstractDto;

import java.util.List;

public interface CommonService<T extends AbstractEntity, D extends AbstractDto> {

    List<T> getAll();

    T getOneById(String id);

    void addOne(T entity);

    void updateOne(T entity);

    void deleteOne(T entity);

    D convertToDto(T entity);

    T convertToEntity(D dto);
}
