package ru.geekbrains.musicportal.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.musicportal.dto.CarDto;
import ru.geekbrains.musicportal.entity.CarEntity;
import ru.geekbrains.musicportal.entity.EngineEntity;
import ru.geekbrains.musicportal.repository.CarRepository;
import ru.geekbrains.musicportal.repository.EngineRepository;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private EngineRepository engineRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, EngineRepository engineRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.engineRepository = engineRepository;
        this.modelMapper = modelMapper;
    }

    public void addNewCar(String mark, int power, String engineName, String number) {
        EngineEntity engine = new EngineEntity();
        engine.setNumber(number);
        engine.setName(engineName);

        CarEntity car = new CarEntity();
        car.setMark(mark);
        car.setPower(power);
        car.setEngine(engine);

        carRepository.addOne(car);
    }

    @Override
    public List<CarEntity> getAll() {
        return carRepository.getAll();
    }

    @Override
    public CarEntity getOneById(String id) {
        return carRepository.getOneById(id, CarEntity.class);
    }

    @Override
    public void addOne(CarEntity entity) {
        carRepository.addOne(entity);
    }

    @Override
    public void updateOne(CarEntity entity) {
        carRepository.updateOne(entity);
    }

    @Override
    public void deleteOne(CarEntity entity) {
        carRepository.deleteOne(entity);
    }

    @Override
    public CarDto convertToDto(CarEntity entity) {
        CarDto dto = modelMapper.map(entity, CarDto.class);
        return dto;
    }

    @Override
    public CarEntity convertToEntity(CarDto dto) {
        CarEntity converted = modelMapper.map(dto, CarEntity.class);
        String dtoId = dto.getId();
        if (dtoId != null) {
            CarEntity entityFromDb = carRepository.getOneById(dtoId, CarEntity.class);
        }
        return converted;
    }

}
