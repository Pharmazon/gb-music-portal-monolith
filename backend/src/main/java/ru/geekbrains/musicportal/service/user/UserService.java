package ru.geekbrains.musicportal.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.musicportal.dto.user.UserDto;
import ru.geekbrains.musicportal.dto.user.UserRegistrationDto;
import ru.geekbrains.musicportal.entity.user.User;
import ru.geekbrains.musicportal.enums.UserRoleEnum;

import java.util.Collection;

public interface UserService extends UserDetailsService  {

    User findByUserName(String username);

    User save(UserRegistrationDto userRegistrationDto);

    User save(User user);

    User getOneById(Long id);

    void deleteOne(User user);

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

    UserDto getOneDtoById(Long id);

    void deleteOneById(Long id);
}
