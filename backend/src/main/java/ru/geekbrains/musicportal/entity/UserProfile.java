package ru.geekbrains.musicportal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ru.geekbrains.musicportal.entity.security.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Getter
    @Setter
    @Id
    //@GeneratedValue
    //@GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "user_id")
    private UUID userID;

    @Getter
    @Setter
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "last_name")
    private String lastName;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "birth_date")
    @CreationTimestamp
    private LocalDateTime birthDate;

    @Getter
    @Setter
    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Getter
    @Setter
    @Column(name = "country")
    private String country;

    @Getter
    @Setter
    @Column(name = "city")
    private String city;

    @Getter
    @Setter
    @Column(name = "skype")
    private String skype;

    @Getter
    @Setter
    @Column(name = "site")
    private String site;

    @Getter
    @Setter
    @Column(name = "hobby")
    private String hobby;

    @JsonBackReference
    @OneToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private User getUser(){
        return user;
    }
}
