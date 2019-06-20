package ru.geekbrains.musicportal.dto;

import lombok.Data;
import ru.geekbrains.musicportal.entity.security.User;

@Data
public class UserDTO {
    private Long id;
    private String login;
    private String password;

    public void fillByEntity(User user){

    }
}
