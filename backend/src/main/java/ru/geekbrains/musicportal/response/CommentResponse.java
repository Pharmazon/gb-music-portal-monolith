package ru.geekbrains.musicportal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.geekbrains.musicportal.response.common.ResponseEnum;

@Getter
@AllArgsConstructor
public enum CommentResponse implements ResponseEnum {
    SUCCESS(100, "Успешно"),
    SUCCESS_DELETED(101, "Комментарий удален успешно"),
    SUCCESS_UPDATED(102, "Комментарий обновлен успешно"),
    SUCCESS_CREATED(103, "Комментарий добавлен успешно"),
    SUCCESS_READ(104, "Комментарий прочитан успешно"),

    ERROR(200, "Возникла ошибка"),
    ERROR_UPDATED(202, "Возникла ошибка при обновлении комментария"),
    ERROR_CREATED(203, "Возникла ошибка при создании комментария"),
    ERROR_READ(204, "Возникла ошибка при чтении комментария"),

    ERROR_NOT_FOUND(205, "Комментарий не найден");

    private int code;
    private String description;
}
