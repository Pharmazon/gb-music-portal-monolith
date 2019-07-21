package ru.geekbrains.musicportal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.geekbrains.musicportal.response.common.ResponseEnum;

@Getter
@AllArgsConstructor
public enum GenreResponse implements ResponseEnum {
    SUCCESS(100, "Успешно"),
    SUCCESS_DELETED(101, "Жанр удален успешно"),
    SUCCESS_UPDATED(102, "Жанр обновлен успешно"),
    SUCCESS_CREATED(103, "Жанр добавлен успешно"),
    SUCCESS_READ(104, "Жанр прочитан успешно"),

    ERROR(200, "Возникла ошибка"),
    ERROR_DELETED(201, "Возникла ошибка при удалении жанра"),
    ERROR_UPDATED(202, "Возникла ошибка при обновлении жанра"),
    ERROR_CREATED(203, "Возникла ошибка при создании жанра"),
    ERROR_READ(204, "Возникла ошибка при чтении жанра"),

    ERROR_NOT_FOUND(205, "Жанр не найден");

    private int code;
    private String description;

}
