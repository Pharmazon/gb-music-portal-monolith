package ru.geekbrains.musicportal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.geekbrains.musicportal.response.common.ResponseEnum;

@Getter
@AllArgsConstructor
public enum TrackResponse implements ResponseEnum {
    SUCCESS(100, "Успешно"),
    SUCCESS_DELETED(101, "Трек удален успешно"),
    SUCCESS_UPDATED(102, "Трек обновлен успешно"),
    SUCCESS_CREATED(103, "Трек добавлен успешно"),
    SUCCESS_READ(104, "Трек прочитан успешно"),
    SUCCESS_MARKED_AS_DELETED(105, "Трек успешно помечен на удаление"),

    ERROR(200, "Возникла ошибка"),
    ERROR_DELETED(201, "Возникла ошибка при удалении трека"),
    ERROR_UPDATED(202, "Возникла ошибка при обновлении трека"),
    ERROR_CREATED(203, "Возникла ошибка при создании трека"),
    ERROR_READ(204, "Возникла ошибка при чтении трека"),
    ERROR_NOT_FOUND(205, "Трек не найден"),
    ERROR_MARKED_AS_DELETED(206, "Ошибка в процессе помечания трека на удаление");

    private int code;
    private String description;

}
