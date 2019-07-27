package ru.geekbrains.musicportal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.geekbrains.musicportal.response.common.ResponseEnum;

@Getter
@AllArgsConstructor
public enum PlaylistResponse implements ResponseEnum {
    SUCCESS(100, "Успешно"),
    SUCCESS_DELETED(101, "Плейлист удален успешно"),
    SUCCESS_UPDATED(102, "Плейлист обновлен успешно"),
    SUCCESS_CREATED(103, "Плейлист добавлен успешно"),
    SUCCESS_READ(104, "Плейлист прочитан успешно"),
    TRACK_SUCCESS_ADDED(105, "Трек успешно добавлен в плейлист"),
    TRACK_SUCCESS_DELETED(106, "Трек успешно удален из плейлиста"),

    ERROR(200, "Возникла ошибка"),
    ERROR_DELETED(201, "Возникла ошибка при удалении плейлиста"),
    ERROR_UPDATED(202, "Возникла ошибка при обновлении плейлиста"),
    ERROR_CREATED(203, "Возникла ошибка при создании плейлиста"),
    ERROR_READ(204, "Возникла ошибка при чтении плейлиста"),
    ERROR_NOT_FOUND(205, "Плейлист не найден"),
    TRACK_ADD_ERROR(206, "Ошибка в процессе добавления трека в плейлист"),
    TRACK_DELETE_ERROR(207, "Ошибка в процессе удаления трека из плейлиста");

    private int code;
    private String description;

}
