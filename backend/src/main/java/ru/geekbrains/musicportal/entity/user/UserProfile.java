package ru.geekbrains.musicportal.entity.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user_profiles")
@EqualsAndHashCode(callSuper = true)
public class UserProfile extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
//    @CreationTimestamp
    private LocalDateTime birthDate;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "skype")
    private String skype;

    @Column(name = "site")
    private String site;

    @Column(name = "hobby")
    private String hobby;

    @JsonBackReference
    @OneToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
