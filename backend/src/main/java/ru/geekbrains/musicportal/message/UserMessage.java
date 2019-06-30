package ru.geekbrains.musicportal.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserMessage {

    ACCOUNT_EXISTS("Пользователь с таким именем уже существует: "),
    INVALID_USERNAME("Неверный логин");

    private String text;
}
