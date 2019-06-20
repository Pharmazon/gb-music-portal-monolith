package ru.geekbrains.musicportal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.geekbrains.musicportal.entity.security.Role;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {

    @NotNull
    private UUID id;

    @NotNull
    private String userName;

    @Nullable
    private String password;

    @Nullable
    private String passwordQuestion;

    @Nullable
    private String PasswordAnswer;

    private boolean isApproved;

    private boolean isLockedOut;

    @NotNull
    private LocalDateTime createDate;

    @NotNull
    private LocalDateTime lastLoginDate;

    @Nullable
    private LocalDateTime lastLockoutDate;

    @NotNull
    private LocalDateTime lastPasswordChangeDate;

    @Nullable
    private String comment;

    private Collection<String> roles;
}
