package ru.geekbrains.musicportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.musicportal.dto.UserRegistrationDto;
import ru.geekbrains.musicportal.service.UserRegisterServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Рест контроллер после заполнения полей на фронте передает посылает дто по адресу /user/registration,
 * дто проходит валидацию и передается в метод save юзерсервиса.
 * На фронт возвращается сообщение success.
 */
@Controller
@RestController
public class RegistrationRestController {
    private UserRegisterServiceImpl userRegisterService;

    @Autowired
    public void setUserRegisterService(UserRegisterServiceImpl userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public String registerUserAccount(@Valid UserRegistrationDto userRegistrationDto, HttpServletRequest request) {
        userRegisterService.save(userRegistrationDto);
        return "Success";
    }


}
