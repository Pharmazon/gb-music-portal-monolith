package ru.geekbrains.musicportal.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.musicportal.dto.UserDto;
import ru.geekbrains.musicportal.entity.user.User;
import ru.geekbrains.musicportal.enums.UserRoleEnum;

import java.util.Collection;

public interface UserService extends UserDetailsService {

    User findByUserName(String username);

    User save(UserDto user);

    User save(User user);

    UserDto getDtoByUsername(String username);

    boolean changePassword(
            String userName,
            String oldPsw,
            String newPsw);

    void registerUser(
            String username,
            String password,
            String email,
            Collection<UserRoleEnum> role);

    boolean changePasswordByPasswordAnswer(
            String userName,
            String newPsw,
            String passwordAnswer);

    boolean isExistsByName(String username);
}
