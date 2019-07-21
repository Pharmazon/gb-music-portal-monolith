package ru.geekbrains.musicportal.dto.user;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.user.User;
import ru.geekbrains.musicportal.marker.UserViews;
import ru.geekbrains.musicportal.validation.PasswordMatches;
import ru.geekbrains.musicportal.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PasswordMatches
public class UserDto extends AbstractDto {

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 25)
    @JsonView(UserViews.All.class)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 8, message = "пароль должен состоять как минимум из 8 символов!")
    @JsonView(UserViews.All.class)
    private String password;

    @NotNull
    @NotEmpty
    @ValidEmail
    @JsonView(UserViews.All.class)
    private String email;

    @NotNull
    @NotEmpty
    @JsonView(UserViews.All.class)
    private String passwordQuestion;

    @NotNull
    @NotEmpty
    @JsonView(UserViews.All.class)
    private String passwordAnswer;

    @JsonView(UserViews.All.class)
    private boolean isApproved;

    @JsonView(UserViews.All.class)
    private Collection<RoleDto> roles;

    @JsonView(UserViews.All.class)
    private Long imageId;

    public UserDto(User user){
        if (user == null) return;
        super.setId(user.getId());
        super.setName(user.getName());
        super.setDescription(user.getDescription());
        super.setCreationDate(user.getCreationDate());
        super.setLastUpdate(user.getLastUpdate());
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        passwordQuestion = user.getPasswordQuestion();
        passwordAnswer = user.getPasswordAnswer();
        isApproved = user.isApproved();
        imageId = user.getImage() == null ? null : user.getImage().getId();
        roles = user.getRoles().stream()
                .map(RoleDto::new)
                .collect(Collectors.toList());
    }

}
