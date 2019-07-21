package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.user.UserDto;
import ru.geekbrains.musicportal.entity.user.User;
import ru.geekbrains.musicportal.marker.UserViews;
import ru.geekbrains.musicportal.response.UserResponse;
import ru.geekbrains.musicportal.response.common.ResponseWrapper;
import ru.geekbrains.musicportal.service.user.UserProfileService;
import ru.geekbrains.musicportal.service.user.UserService;

import javax.validation.Valid;
import java.util.Collection;

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

    @JsonView(UserViews.All.class)
    @GetMapping
    public ResponseWrapper<Collection<UserDto>> getAll() {
        Collection<UserDto> dtos = userService.findAllDtos();
        if (dtos != null) {
            return ResponseWrapper.ok(dtos, UserResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(UserResponse.ERROR_NOT_FOUND);
    }

    @JsonView(UserViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper<UserDto> getOne(@PathVariable("id") Long id) {
        UserDto dto = userService.findOneDtoById(id);
        if (dto != null) {
            return ResponseWrapper.ok(dto, UserResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(UserResponse.ERROR_NOT_FOUND);
    }

    @JsonView(UserViews.All.class)
    @PutMapping
    public ResponseWrapper<UserDto> update(@Valid UserDto dto) {
        User converted = userService.convertToEntity(dto);
        User artist = userService.saveOrUpdate(converted);
        if (converted != null && artist != null) {
            return ResponseWrapper.ok(dto, UserResponse.SUCCESS_UPDATED);
        }
        return ResponseWrapper.notFound(UserResponse.ERROR_NOT_FOUND);
    }

    @JsonView(UserViews.All.class)
    @DeleteMapping("{id}")
    public ResponseWrapper delete(@PathVariable("id") Long id) {
        boolean deleted = userService.deleteById(id);
        if (deleted) {
            return ResponseWrapper.success(UserResponse.SUCCESS_DELETED);
        }
        return ResponseWrapper.notFound(UserResponse.ERROR_NOT_FOUND);
    }

}
