package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T extends AbstractEntity> {

    T getOneById(String id, Class<T> aClass);

    List<T> getEntities(final TypedQuery<T> query);

    T getEntity(final TypedQuery<T> query);

    void addOne(final T entity);

    void addAll(final List<T> entities);

    T updateOne(final T entity);

    void updateAll(final List<T> entities);

    void deleteOne(final T entity);

    void deleteAll(final List<T> entities);

    List<T> getAll();

    EntityManager getEntityManager();
}
