package ru.geekbrains.musicportal.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.user.UserProfile;

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

    public UserProfileDto(UserProfile userProfile) {
        this.firstName = userProfile.getFirstName();
        this.lastName = userProfile.getLastName();
        this.email = userProfile.getEmail();
        this.birthDate = userProfile.getBirthDate();
        this.mobilePhone = userProfile.getMobilePhone();
        this.country = userProfile.getCountry();
        this.city = userProfile.getCity();
        this.skype = userProfile.getSkype();
        this.site = userProfile.getSite();
        this.hobby = userProfile.getHobby();
    }
}
