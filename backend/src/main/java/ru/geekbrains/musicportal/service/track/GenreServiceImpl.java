package ru.geekbrains.musicportal.service.track;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.track.GenreDto;
import ru.geekbrains.musicportal.entity.track.Genre;
import ru.geekbrains.musicportal.repository.GenreRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;
    private ModelMapper modelMapper;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository,
                            ModelMapper modelMapper) {
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<Genre> getById(Long id){
        return genreRepository.findById(id);
    }

    public GenreDto getByName(String name){
        return genreRepository.findOneByName(name);
    }

    @Override
    public Genre saveOrUpdate(Genre entity) {
        return genreRepository.save(entity);
    }

    @Override
    public Optional<Genre> findOneEntityById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public Collection<GenreDto> findAllDto() {
        return null;
    }

    @Override
    public GenreDto findOneDtoById(Long id) {
        return genreRepository.findOneById(id);
    }

    @Override
    public Genre convertToEntity(GenreDto dto) {
        return modelMapper.map(dto, Genre.class);
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public Page<Genre> getGenresWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Genre> specification) {
        return genreRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));
    }
}
