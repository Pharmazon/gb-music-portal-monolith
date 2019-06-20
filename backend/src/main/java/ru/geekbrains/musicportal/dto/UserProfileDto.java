package ru.geekbrains.musicportal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileDto implements Serializable {
    @NotNull
    private UUID userID;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private String email;

    @Nullable
    private LocalDateTime birthDate;

    @Nullable
    private String mobilePhone;

    @Nullable
    private String country;

    @Nullable
    private String city;

    @Nullable
    private String skype;

    @Nullable
    private String site;

    @Nullable
    private String hobby;
}
