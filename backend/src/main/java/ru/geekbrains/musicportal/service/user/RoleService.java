package ru.geekbrains.musicportal.service.user;

import ru.geekbrains.musicportal.entity.user.Role;

import java.util.Optional;

public interface RoleService {

    Role save(Role entity);

    Optional<Role> findById(Long id);

    boolean isExistsByName(String name);

}
