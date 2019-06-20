package ru.geekbrains.musicportal.service.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.musicportal.entity.security.User;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    boolean save(User user);
    boolean changePassword(String userName, String oldPsw, String newPsw);
    String encodePassword(String passwordRaw, String passwordSalt);
}
