package ru.geekbrains.musicportal.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.musicportal.dto.user.UserDto;
import ru.geekbrains.musicportal.dto.user.UserRegistrationDto;
import ru.geekbrains.musicportal.entity.user.User;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface UserService extends UserDetailsService, CommonService<User, UserDto> {

    User findByUserName(String username);

    User save(UserRegistrationDto userRegistrationDto);

    UserDto getDtoByUsername(String username);

    boolean changePassword(String userName,
                           String oldPsw,
                           String newPsw);

    void registerUser(String username,
                      String password,
                      String email);

    boolean changePasswordByPasswordAnswer(String userName,
                                           String newPsw,
                                           String passwordAnswer);

    boolean isExistsByName(String username);

}
