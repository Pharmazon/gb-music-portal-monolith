package ru.geekbrains.musicportal.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.musicportal.dto.EngineDto;
import ru.geekbrains.musicportal.entity.CarEntity;
import ru.geekbrains.musicportal.entity.EngineEntity;
import ru.geekbrains.musicportal.repository.EngineRepository;

import java.util.List;

@Service
public class EngineServiceImpl implements EngineService {

    private EngineRepository engineRepository;
    private ModelMapper modelMapper;

    @Autowired
    public EngineServiceImpl(EngineRepository engineRepository, ModelMapper modelMapper) {
        this.engineRepository = engineRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EngineEntity> getAll() {
        return null;
    }

    @Override
    public EngineEntity getOneById(String id) {
        return null;
    }

    @Override
    public void addOne(EngineEntity entity) {

    }

    @Override
    public void updateOne(EngineEntity entity) {

    }

    @Override
    public void deleteOne(EngineEntity entity) {

    }

    @Override
    public EngineDto convertToDto(EngineEntity entity) {
        EngineDto dto = modelMapper.map(entity, EngineDto.class);
        return dto;
    }

    @Override
    public EngineEntity convertToEntity(EngineDto dto) {
        EngineEntity converted = modelMapper.map(dto, EngineEntity.class);
        String dtoId = dto.getId();
        if (dtoId != null) {
            EngineEntity entityFromDb = engineRepository.getOneById(dtoId, EngineEntity.class);
        }
        return converted;
    }

    //бизнес-логика
}
