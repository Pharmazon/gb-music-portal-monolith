package ru.geekbrains.musicportal.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.user.UserDto;
import ru.geekbrains.musicportal.service.user.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Рест контроллер после заполнения полей с фронта принимает дто по адресу /user/registration,
 * дто проходит валидацию и передается в метод saveOrUpdate юзерсервиса.
 * На фронт возвращается сообщение success.
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class RegistrationRestController {

    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("registerForm")
    public UserDto getUserDto() {
        return new UserDto();
    }

    @PostMapping("registration")
    public String registerUserAccount(@Valid UserDto userDto,
                                      HttpServletRequest request) {
        userService.save(userDto);
        return "Success";
    }


}
