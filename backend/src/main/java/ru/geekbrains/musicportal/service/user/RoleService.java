package ru.geekbrains.musicportal.service.user;

import ru.geekbrains.musicportal.entity.user.Role;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface RoleService extends CommonService<Role> {

    boolean isExistsByName(String name);

}