package ru.geekbrains.musicportal.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.CarDto;
import ru.geekbrains.musicportal.dto.http.ResponseWrapper;
import ru.geekbrains.musicportal.entity.CarEntity;
import ru.geekbrains.musicportal.enums.CarResponse;
import ru.geekbrains.musicportal.service.CarService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cars")
public class CarController {

    private CarService carService;
    private ModelMapper modelMapper;

    @Autowired
    public CarController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String getGreeting() {
        return "Hello, user! You're in CARS.";
    }

    @GetMapping("list")
    public ResponseEntity<ResponseWrapper> getCarsList() {
        List<CarEntity> cars = carService.getAll();
        return new ResponseEntity<>(new ResponseWrapper(CarResponse.READ_SUCCESS, cars), HttpStatus.OK);
    }

    @GetMapping("all")
    public List<CarDto> getCars() {
        List<CarEntity> cars = carService.getAll();
        return cars.stream()
                .map(CarDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CarDto getOneById(@PathVariable("id") String id) {
        CarEntity car = carService.getOneById(id);
        return new CarDto(car);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarEntity create(@RequestBody CarDto dto) {
        CarEntity entity = carService.convertToEntity(dto);
        carService.addOne(entity);
        return entity;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable("id") CarEntity entityFromDb,
            @RequestBody CarDto dto) {
        BeanUtils.copyProperties(dto, entityFromDb, "id");
        carService.updateOne(entityFromDb);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        CarEntity entity = carService.getOneById(id);
        carService.deleteOne(entity);
    }

}
