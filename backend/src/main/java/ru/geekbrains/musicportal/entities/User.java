package ru.geekbrains.musicportal.entities;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String login;
    private String password;
}
