package ru.geekbrains.musicportal.exception;

import ru.geekbrains.musicportal.message.UserMessage;

/**
 * Исключение котороя выбрасывается если пытаться сохранить уже существующего юзера.
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super(UserMessage.ACCOUNT_EXISTS + username);
    }
}
