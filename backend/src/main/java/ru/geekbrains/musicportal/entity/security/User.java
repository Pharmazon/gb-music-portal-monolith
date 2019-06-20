package ru.geekbrains.musicportal.entity.security;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@SecondaryTables({
        @SecondaryTable(name = "user_membership")
})
public class User implements UserDetails {

    public User(){
        userMembership = new UserMembership();
        roles = new ArrayList<>();
    }

    @Getter
    @Setter
    @Id
    //@GeneratedValue
    //@GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "user_id")
    private UUID userID;

    @Getter
    @Setter
    @Column(name = "user_name")
    private String userName;

    @Getter
    @Setter
    @Column(name = "Lowered_user_name")
    private String loweredUserName;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @Column(name = "password_salt")
    private String passwordSalt;

    @Getter
    @Setter
    @Column(name = "password_format")
    private String passwordFormat;

    @Getter
    @Setter
    @Column(name = "password_question")
    private String passwordQuestion;

    @Getter
    @Setter
    @Column(name = "password_answer")
    private String PasswordAnswer;

    @Getter
    @Setter
    @AttributeOverrides({
            @AttributeOverride(name="street", column=@Column(table="user_membership")),
            @AttributeOverride(name="building", column=@Column(table="user_membership"))
    })
    private UserMembership userMembership;

    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "users_in_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public Collection<Role> getRoles(){
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return loweredUserName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !userMembership.isLockedOut();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return userMembership.isApproved();
    }

    @JsonBackReference
    @OneToOne(mappedBy = "user", cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    private UserProfile userProfile;

    private UserProfile getCustomer(){
        return userProfile;
    }
}

