package ru.geekbrains.musicportal.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRoleEnum {

    USER("user"),
    ADMINISTRATOR("admin");

    private String name;
}
