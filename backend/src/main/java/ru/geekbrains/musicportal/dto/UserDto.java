package ru.geekbrains.musicportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.user.Role;
import ru.geekbrains.musicportal.entity.user.User;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends AbstractDto {

    private String username;

    private String password;

    private String passwordQuestion;

    private String passwordAnswer;

    private String comment;

    private boolean approved;

    private List<Role> roles;

    public UserDto(User user){
        super.setId(user.getId());
        super.setName(user.getName());
        super.setDescription(user.getDescription());
        username = user.getUsername();
        password = user.getPassword();
        passwordQuestion = user.getPasswordQuestion();
        passwordAnswer = user.getPasswordAnswer();
        comment = "";
        approved = false;
    }
}
