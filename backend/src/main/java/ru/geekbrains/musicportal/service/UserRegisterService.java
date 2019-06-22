package ru.geekbrains.musicportal.service;



import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.musicportal.dto.UserRegistrationDto;
import ru.geekbrains.musicportal.entity.TestUser;
import ru.geekbrains.musicportal.exception.UserAlreadyExistException;

/**
 * Интерфейс юзер сервиса наследующий юзер дитеилс сервис.
 */
public interface UserRegisterService extends UserDetailsService {
    TestUser findByUserName(String username);
    TestUser findByEmail(String email);
    boolean save(UserRegistrationDto systemUser) throws UserAlreadyExistException;
}
