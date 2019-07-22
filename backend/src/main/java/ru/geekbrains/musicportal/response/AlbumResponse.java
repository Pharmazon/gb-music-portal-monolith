package ru.geekbrains.musicportal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.geekbrains.musicportal.response.common.ResponseEnum;

@Getter
@AllArgsConstructor
public enum AlbumResponse implements ResponseEnum {
    SUCCESS(100, "Успешно"),
    SUCCESS_DELETED(101, "Альбом удален успешно"),
    SUCCESS_UPDATED(102, "Альбом обновлен успешно"),
    SUCCESS_CREATED(103, "Альбом добавлен успешно"),
    SUCCESS_READ(104, "Альбом прочитан успешно"),

    ERROR(200, "Возникла ошибка"),
    ERROR_DELETED(201, "Возникла ошибка при удалении альбома"),
    ERROR_UPDATED(202, "Возникла ошибка при обновлении альбома"),
    ERROR_CREATED(203, "Возникла ошибка при создании альбома"),
    ERROR_READ(204, "Возникла ошибка при чтении альбома"),
    ERROR_NOT_FOUND(205, "Альбом не найден");

    private int code;
    private String description;

}
