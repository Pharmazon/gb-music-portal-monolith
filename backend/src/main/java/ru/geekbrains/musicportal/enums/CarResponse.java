package ru.geekbrains.musicportal.enums;

import lombok.AllArgsConstructor;
import ru.geekbrains.musicportal.dto.http.Response;

import java.io.Serializable;

@AllArgsConstructor
public enum CarResponse implements Response, Serializable {
    READ_SUCCESS(100, "Список успешно получен."),
    ADD_SUCCESS(100, "Машина успешно добавлена."),
    DELETE_SUCCESS(100, "Машина успешно удалена.");
    //и далее

    private int code;

    private String reasonPhrase;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    @Override
    public Response setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
        return null;
    }
}
