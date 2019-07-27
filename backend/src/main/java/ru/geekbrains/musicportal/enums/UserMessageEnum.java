package ru.geekbrains.musicportal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserMessageEnum {

    ACCOUNT_EXISTS("Пользователь с таким именем уже существует: "),
    INVALID_USERNAME("Неверный логин");

    private String text;
}
