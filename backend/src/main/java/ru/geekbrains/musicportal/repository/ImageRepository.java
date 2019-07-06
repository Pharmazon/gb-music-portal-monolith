package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.image.Image;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<Image, Long>, JpaSpecificationExecutor<Image> {

    <T> T findById(Long id, Class<T> type);
}
