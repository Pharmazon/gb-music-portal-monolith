package ru.geekbrains.musicportal.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.dto.image.ImageDto;
import ru.geekbrains.musicportal.entity.user.Role;
import ru.geekbrains.musicportal.entity.user.User;
import ru.geekbrains.musicportal.validation.PasswordMatches;
import ru.geekbrains.musicportal.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PasswordMatches
public class UserDto extends AbstractDto {

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 25)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 8, message = "пароль должен состоять как минимум из 8 символов!")
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    private String passwordQuestion;

    @NotNull
    @NotEmpty
    private String passwordAnswer;

    private boolean approved;

    private List<RoleDto> roles;

    private ImageDto image;

    public UserDto(User user){
        if (user == null) return;

        super.setId(user.getId());
        super.setName(user.getName());
        super.setDescription(user.getDescription());
        username = user.getUsername();
        password = user.getPassword();
        passwordQuestion = user.getPasswordQuestion();
        passwordAnswer = user.getPasswordAnswer();
        approved = user.isApproved();
        image = new ImageDto(user.getImage());

        for (Role role:user.getRoles()) {
            roles.add(new RoleDto(role));
        }
    }

}
