package ru.geekbrains.musicportal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.geekbrains.musicportal.response.common.ResponseEnum;

@Getter
@AllArgsConstructor
public enum LikeResponse implements ResponseEnum {
    SUCCESS(100, "Успешно"),
    SUCCESS_DELETED(101, "Лайн удален успешно"),
    SUCCESS_UPDATED(102, "Лайк обновлен успешно"),
    SUCCESS_CREATED(103, "Лайк добавлен успешно"),
    SUCCESS_READ(104, "Лайк прочитан успешно"),

    ERROR(200, "Возникла ошибка"),
    ERROR_DELETED(201, "Возникла ошибка при удалении лайка"),
    ERROR_UPDATED(202, "Возникла ошибка при обновлении лайка"),
    ERROR_CREATED(203, "Возникла ошибка при создании лайка"),
    ERROR_READ(204, "Возникла ошибка при чтении лайка"),

    ERROR_NOT_FOUND(205, "Лайк не найден");

    private int code;
    private String description;

}
