package ru.geekbrains.musicportal.service.user;

import ru.geekbrains.musicportal.entity.user.Role;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface RoleService extends CommonService<Role> {

    void fillTableByAll();

    boolean isExist(String name);

}
