package ru.geekbrains.musicportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserProfileDto extends AbstractDto {

    private String firstName;

    private String lastName;

    private String email;

    private LocalDateTime birthDate;

    private String mobilePhone;

    private String country;

    private String city;

    private String skype;

    private String site;

    private String hobby;

}
