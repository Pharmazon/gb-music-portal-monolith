package ru.geekbrains.musicportal.bootstrap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.musicportal.service.CarService;

//@Component
//TODO: Не работает, хотя должен при старте заполнять базу
public class Bootstrap implements InitializingBean {

    private CarService carService;

//    @Autowired
    public Bootstrap(CarService carService) {
        this.carService = carService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        carService.addNewCar("Audi", 100, "honda", "efiwe");
        carService.addNewCar("BMW", 200, "kawasaki", "rky4h5k4");
        carService.addNewCar("Lada", 500, "lada", "efi57i57iwe");
    }

}
