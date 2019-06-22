package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.CarEntity;

import java.util.List;

@Repository
public interface CarRepository extends BaseRepository<CarEntity> {

    @Query("SELECT c FROM CarEntity c WHERE c.mark = :carMark")
    List<CarEntity> getAllByMark(String carMark);

    @Query("SELECT c FROM CarEntity c")
    List<CarEntity> getAll();

    @Query("SELECT c FROM CarEntity c INNER JOIN FETCH c.engine e WHERE e.name = :engineName")
    List<CarEntity> getAllByEngineName(String engineName);

}
