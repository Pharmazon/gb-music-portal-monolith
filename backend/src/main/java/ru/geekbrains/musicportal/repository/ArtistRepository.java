package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.artist.ArtistDto;
import ru.geekbrains.musicportal.entity.artist.Artist;

@Repository
public interface ArtistRepository extends CommonRepository<ArtistDto>, CrudRepository<Artist, Long>,
        JpaSpecificationExecutor<Artist> {

}
