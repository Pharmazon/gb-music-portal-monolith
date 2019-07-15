package ru.geekbrains.musicportal.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.user.UserDto;
import ru.geekbrains.musicportal.dto.user.UserRegistrationDto;
import ru.geekbrains.musicportal.service.user.UserServiceImpl;

import javax.validation.Valid;

/**
 * Рест контроллер после заполнения полей с фронта принимает дто по адресу /registration,
 * дто проходит валидацию и передается в метод saveOrUpdate юзерсервиса.
 * На фронт возвращается статус OK.
 */
@CrossOrigin
@RestController
@RequestMapping("/registration")
public class UserRegistrationRestController {

    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDto getUserDto() {
        return new UserDto();
    }

    @PostMapping
    public ResponseEntity<?> registerUserAccount(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        userService.save(userRegistrationDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
