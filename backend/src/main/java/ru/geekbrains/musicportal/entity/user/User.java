package ru.geekbrains.musicportal.entity.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.geekbrains.musicportal.entity.AbstractEntity;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.entity.track.MusicGroup;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_users")
@EqualsAndHashCode(callSuper = true)
@SecondaryTables({
        @SecondaryTable(name = "user_membership")
})
public class User extends AbstractEntity implements UserDetails {

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "password_salt")
    private String passwordSalt;

    @Column(name = "password_format")
    private String passwordFormat;

    @Column(name = "password_question")
    private String passwordQuestion;

    @Column(name = "password_answer")
    private String passwordAnswer;

    @AttributeOverrides({
            @AttributeOverride(
                    name = "street",
                    column = @Column(table = "user_membership")),
            @AttributeOverride(
                    name = "building",
                    column = @Column(table = "user_membership"))
    })
    private UserMembership userMembership;

    @JsonBackReference
    @ManyToMany(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "join_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return username.toLowerCase();
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
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER)
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private MusicGroup group;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Collection<Playlist> playlists;
}

