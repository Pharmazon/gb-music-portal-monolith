package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.EngineEntity;

import java.util.List;

@Repository
public interface EngineRepository extends BaseRepository<EngineEntity> {

    @Query("SELECT e FROM EngineEntity e WHERE e.name = :engineName")
    List<EngineEntity> getAllByName(String engineName);

    @Query("SELECT e FROM EngineEntity e")
    List<EngineEntity> getAll();
}
