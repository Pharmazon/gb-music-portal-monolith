package ru.geekbrains.musicportal.service;

import ru.geekbrains.musicportal.dto.CarDto;
import ru.geekbrains.musicportal.entity.CarEntity;

public interface CarService extends CommonService<CarEntity, CarDto> {

    void addNewCar(String mark, int power, String engineName, String number);

}
