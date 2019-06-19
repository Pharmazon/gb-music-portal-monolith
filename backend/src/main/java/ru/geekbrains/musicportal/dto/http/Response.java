package ru.geekbrains.musicportal.dto.http;

public interface Response {

    int getCode();

    String getReasonPhrase();

    Response setReasonPhrase(String reasonPhrase);
}
