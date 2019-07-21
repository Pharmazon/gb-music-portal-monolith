package ru.geekbrains.musicportal.dto.user;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import ru.geekbrains.musicportal.entity.user.Role;
import ru.geekbrains.musicportal.marker.UserViews;

import java.io.Serializable;

@Getter
public class RoleDto implements Serializable {

    @JsonView(UserViews.All.class)
    private Long id;

    @JsonView(UserViews.All.class)
    private String name;

    public RoleDto(Role role) {
        id = role.getId();
        name = role.getName();
    }
}
