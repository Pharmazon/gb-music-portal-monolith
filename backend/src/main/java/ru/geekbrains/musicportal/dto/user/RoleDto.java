package ru.geekbrains.musicportal.dto.user;

import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.user.Role;

public class RoleDto extends AbstractDto {

    public RoleDto(Role role) {
        super.setId(role.getId());
        super.setName(role.getName());
        super.setDescription(role.getDescription());
    }
}
