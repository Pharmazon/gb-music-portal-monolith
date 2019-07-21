package ru.geekbrains.musicportal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.geekbrains.musicportal.response.common.ResponseEnum;

@Getter
@AllArgsConstructor
public enum ArtistResponse implements ResponseEnum {
    SUCCESS(100, "Успешно"),
    SUCCESS_DELETED(101, "Исполнитель удален успешно"),
    SUCCESS_UPDATED(102, "Исполнитель обновлен успешно"),
    SUCCESS_CREATED(103, "Исполнитель добавлен успешно"),
    SUCCESS_READ(104, "Исполнитель прочитан успешно"),

    ERROR(200, "Возникла ошибка"),
    ERROR_DELETED(201, "Возникла ошибка при удалении исполнителя"),
    ERROR_UPDATED(202, "Возникла ошибка при обновлении исполнителя"),
    ERROR_CREATED(203, "Возникла ошибка при создании исполнителя"),
    ERROR_READ(204, "Возникла ошибка при чтении исполнителя"),
    ERROR_NOT_FOUND(205, "Исполнитель не найден");

    private int code;
    private String description;

}
