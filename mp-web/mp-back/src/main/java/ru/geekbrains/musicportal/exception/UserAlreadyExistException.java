package ru.geekbrains.musicportal.exception;

/**
 * Исключение котороя выбрасывается если пытаться сохранить уже существующего юзера.
 */
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(final String message) {
        super(message);
    }
}
