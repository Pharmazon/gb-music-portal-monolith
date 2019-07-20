package ru.geekbrains.musicportal.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.user.UserProfileDto;
import ru.geekbrains.musicportal.entity.user.UserProfile;
import ru.geekbrains.musicportal.service.user.UserProfileService;
import ru.geekbrains.musicportal.service.user.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserRestController {

    private UserService userService;
    private UserProfileService userProfileService;

    @Autowired
    public UserRestController(UserService userService,
                              UserProfileService userProfileService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @GetMapping("{id}")
    public UserProfileDto getOne(@PathVariable("id") Long id) {
        return userProfileService.findOneDtoById(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id,
                       UserProfileDto userProfileDto) {
        UserProfile profile = userProfileService.convertToEntity(userProfileDto);
        userProfileService.saveOrUpdate(profile);
    }

    @DeleteMapping("{id}")
    public void deleteOne(@PathVariable("id") Long userId) {
        userService.deleteOneById(userId);
    }

}
