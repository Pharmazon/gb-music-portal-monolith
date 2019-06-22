package ru.geekbrains.musicportal.dto;

import lombok.*;
import ru.geekbrains.musicportal.validation.PasswordMatches;
import ru.geekbrains.musicportal.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *Дто для регистрации пользователя.
 * Используютя две кастомные аннотации @PasswordMatches проверяет совпал ли пароль и
 * @ValdEmail проверяет что email написан верно.
 * Так же включены стандартные аннотации @NotNull и @NotEmpty.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@PasswordMatches
public class UserRegistrationDto implements Serializable {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
}
