package ru.geekbrains.musicportal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.geekbrains.musicportal.response.common.ResponseEnum;

@Getter
@AllArgsConstructor
public enum UserResponse implements ResponseEnum {
    SUCCESS(100, "Успешно"),
    SUCCESS_DELETED(101, "Пользователь удален успешно"),
    SUCCESS_UPDATED(102, "Пользователь обновлен успешно"),
    SUCCESS_CREATED(103, "Пользователь добавлен успешно"),
    SUCCESS_READ(104, "Пользователь прочитан успешно"),
    SUCCESS_AUTHENTICATED(105, "Пользователь успешно авторизован"),
    SUCCESS_REGISTERED(105, "Пользователь успешно зарегистрирован"),

    ERROR(200, "Возникла ошибка"),
    ERROR_DELETED(201, "Возникла ошибка при удалении пользователя"),
    ERROR_UPDATED(202, "Возникла ошибка при обновлении пользователя"),
    ERROR_CREATED(203, "Возникла ошибка при создании пользователя"),
    ERROR_READ(204, "Возникла ошибка при чтении пользователя"),
    ERROR_NOT_FOUND(205, "Пользователь не найден"),
    ERROR_REGISTERED(206, "Возникла ошибка при регистрации пользователя");

    private int code;
    private String description;

}
