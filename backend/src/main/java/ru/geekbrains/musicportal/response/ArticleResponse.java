package ru.geekbrains.musicportal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.geekbrains.musicportal.response.common.ResponseEnum;

@Getter
@AllArgsConstructor
public enum ArticleResponse implements ResponseEnum {
    SUCCESS(100, "Успешно"),
    SUCCESS_DELETED(101, "Статья удалена успешно"),
    SUCCESS_UPDATED(102, "Статья обновлена успешно"),
    SUCCESS_CREATED(103, "Статья добавлена успешно"),
    SUCCESS_READ(104, "Статья прочитана успешно"),

    ERROR(200, "Возникла ошибка"),
    ERROR_DELETED(201, "Возникла ошибка при удалении статьи"),
    ERROR_UPDATED(202, "Возникла ошибка при обновлении статьи"),
    ERROR_CREATED(203, "Возникла ошибка при создании статьи"),
    ERROR_READ(204, "Возникла ошибка при чтении статьи"),

    ERROR_NOT_FOUND(205, "Статья не найдена");

    private int code;
    private String description;

}
