package ru.geekbrains.musicportal.controller.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.musicportal.dto.user.UserDto;
import ru.geekbrains.musicportal.entity.user.User;
import ru.geekbrains.musicportal.service.user.UserServiceImpl;

import javax.validation.Valid;

/**
 * Стандартный контроллер возвращающий по адресу /register/showRegistrationForm форму регистрации,
 * после ввода нужных полей в форму идет сабмит ао адресу /register/processRegistrationForm с
 * последующей валидацие ДТО.
 */
@Controller
@RequestMapping("/register")
public class UserRegistrationController {

    private final UserServiceImpl userService;

    @Autowired
    public UserRegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String showLoginPage() {
        return "index";
    }

    @GetMapping("showRegistrationForm")
    public String showRegisterForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userRegistrationDto", userDto);
        return "registration";
    }

    @PostMapping("processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("userDto") UserDto userDto,
                                          BindingResult theBindingResult, Model theModel) {
        String userName = userDto.getUsername();
        if (theBindingResult.hasErrors()) {
            return "registration";
        }
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            theModel.addAttribute("userRegistrationDto", userDto);
            theModel.addAttribute("registrationError", "User with current username already exists");
            return "registration";
        }
        userService.save(userDto);
        return "registration-confirmation";
    }
}
