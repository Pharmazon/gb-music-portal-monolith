package ru.geekbrains.musicportal.service.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.musicportal.entity.security.User;
import ru.geekbrains.musicportal.dto.UserDto;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    User save(UserDto user);
    boolean changePassword(String userName, String oldPsw, String newPsw);
    boolean changePasswordByPasswordAnswer(String userName, String newPsw, String passwordAnswer);
    boolean addUserInRole(String userName, String roleName);
    boolean removeUserFromRole(String userName, String roleName);
}
