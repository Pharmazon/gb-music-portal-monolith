package ru.geekbrains.musicportal.bootstrap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.musicportal.enums.UserRoleEnum;
import ru.geekbrains.musicportal.service.user.RoleService;
import ru.geekbrains.musicportal.service.user.UserService;

@Component
public class Bootstrap implements InitializingBean {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public Bootstrap(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        roleService.fillTableByAll();
        userService.registerUser("admin", "admin", UserRoleEnum.ADMINISTRATOR);
        userService.registerUser("user", "user", UserRoleEnum.USER);
    }
}
