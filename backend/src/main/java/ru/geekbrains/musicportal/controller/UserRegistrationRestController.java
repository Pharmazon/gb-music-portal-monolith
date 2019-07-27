package ru.geekbrains.musicportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.user.UserDto;
import ru.geekbrains.musicportal.dto.user.UserRegistrationDto;
import ru.geekbrains.musicportal.entity.user.User;
import ru.geekbrains.musicportal.response.UserResponse;
import ru.geekbrains.musicportal.response.common.ResponseWrapper;
import ru.geekbrains.musicportal.service.user.UserService;

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

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDto getUserDto() {
        return new UserDto();
    }

    @PostMapping
    public ResponseWrapper registerUserAccount(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        User user = userService.save(userRegistrationDto);
        return user != null ? ResponseWrapper.success(UserResponse.SUCCESS_REGISTERED) :
                ResponseWrapper.success(UserResponse.ERROR_REGISTERED);
    }
}
