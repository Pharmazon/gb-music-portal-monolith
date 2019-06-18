package ru.geekbrains.musicportal.repository;

import lombok.Getter;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.musicportal.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@NoRepositoryBean
public abstract class AbstractRepository<T extends AbstractEntity> implements BaseRepository<T> {

    @Getter
    @PersistenceContext
    private EntityManager entityManager;

    public T getOneById(String id, Class<T> aClass) {
        return entityManager.find(aClass, id);
    }

    public List<T> getEntities(TypedQuery<T> query) {
        final List<T> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList;
    }

    public T getEntity(TypedQuery<T> query) {
        final List<T> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public void addOne(T entity) {
        entityManager.persist(entity);
    }

    public void addAll(List<T> entities) {
        for (T entity : entities) addOne(entity);
    }

    public T updateOne(T entity) {
        return entityManager.merge(entity);
    }

    public void updateAll(List<T> entities) {
        for (T entity : entities) entityManager.merge(entity);
    }

    public void deleteOne(T entity) {
        entityManager.remove(entity);
    }

    public void deleteAll(List<T> entities) {
        for (T entity : entities) deleteOne(entity);
    }
}
