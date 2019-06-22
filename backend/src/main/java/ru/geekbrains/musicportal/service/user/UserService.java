package ru.geekbrains.musicportal.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.musicportal.dto.UserDto;
import ru.geekbrains.musicportal.entity.user.User;

public interface UserService extends UserDetailsService {

    User findByUserName(String username);

    User save(UserDto user);

    User save(User user);

    boolean changePassword(String userName, String oldPsw, String newPsw);

    boolean changePasswordByPasswordAnswer(
            String userName,
            String newPsw,
            String passwordAnswer);

    boolean addUserInRole(String userName, String roleName);

    boolean removeUserFromRole(String userName, String roleName);

}
