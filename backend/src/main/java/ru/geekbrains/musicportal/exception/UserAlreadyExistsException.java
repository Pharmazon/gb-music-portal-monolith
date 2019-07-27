package ru.geekbrains.musicportal.exception;

import ru.geekbrains.musicportal.enums.UserMessageEnum;

/**
 * Исключение котороя выбрасывается если пытаться сохранить уже существующего юзера.
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super(UserMessageEnum.ACCOUNT_EXISTS + username);
    }
}
