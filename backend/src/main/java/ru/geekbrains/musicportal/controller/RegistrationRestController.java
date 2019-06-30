package ru.geekbrains.musicportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.musicportal.dto.UserDto;
import ru.geekbrains.musicportal.service.user.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Рест контроллер после заполнения полей с фронта принимает дто по адресу /user/registration,
 * дто проходит валидацию и передается в метод save юзерсервиса.
 * На фронт возвращается сообщение success.
 */
@RestController
public class RegistrationRestController {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/registerForm")
    public UserDto getUserDto() {
        return new UserDto();
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public String registerUserAccount(@Valid UserDto userDto, HttpServletRequest request) {
        userService.save(userDto);
        return "Success";
    }


}
