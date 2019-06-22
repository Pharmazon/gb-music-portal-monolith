package ru.geekbrains.musicportal.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.musicportal.dto.UserRegistrationDto;
import ru.geekbrains.musicportal.entity.TestUser;
import ru.geekbrains.musicportal.service.UserRegisterService;

import javax.validation.Valid;

/**
 * Стандартный контроллер возвращающий по адресу /register/showRegistrationForm форму регистрации,
 * после ввода нужных полей в форму идет сабмит ао адресу /register/processRegistrationForm с
 * последующей валидацие ДТО.
 */
@Controller
@RequestMapping(value = "/register")
public class RegistrationController {

    @Autowired
    private UserRegisterService userRegisterService;

    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "index";
    }

    @GetMapping(value = "/showRegistrationForm")
    public String showRegisterForm(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("userRegistrationDto", userRegistrationDto);
        return "registration";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
                                          BindingResult theBindingResult, Model theModel) {
        String userName = userRegistrationDto.getUserName();
        if (theBindingResult.hasErrors()) {
            return "registration";
        }
        TestUser existing = userRegisterService.findByUserName(userName);
        if (existing != null) {
            theModel.addAttribute("userRegistrationDto", userRegistrationDto);
            theModel.addAttribute("registrationError", "User with current username already exists");
            return "registration";
        }
        userRegisterService.save(userRegistrationDto);
        return "registration-confirmation";
    }
}
