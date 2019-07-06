package ru.geekbrains.musicportal.entity.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.geekbrains.musicportal.entity.band.Band;
import ru.geekbrains.musicportal.entity.blog.Article;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.entity.blog.Like;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.image.Image;
import ru.geekbrains.musicportal.entity.playlist.Playlist;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_users")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity implements UserDetails {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_salt")
    private String passwordSalt;

    @Column(name = "password_format")
    private String passwordFormat;

    @Column(name = "password_question")
    private String passwordQuestion;

    @Column(name = "password_answer")
    private String passwordAnswer;

    @Column(name = "is_approved", nullable = false)
    private boolean isApproved;

    @Column(name = "is_locked_out", nullable = false)
    private boolean isLockedOut;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    @Column(name = "last_lockout_date")
    private LocalDateTime lastLockoutDate;

    @Column(name = "last_password_change_date")
    private LocalDateTime lastPasswordChangeDate;

    @Column(name = "failed_password_attempt_count")
    private int failedPasswordAttemptCount;

    @Column(name = "failed_password_attempt_window_start")
    private LocalDateTime failedPasswordAttemptWindowStart;

    @Column(name = "failed_password_answer_attempt_count")
    private int failedPasswordAnswerAttemptCount;

    @Column(name = "failed_password_answer_attempt_window_start")
    private LocalDateTime failedPasswordAnswerAttemptWindowStart;

    @Column(name = "comment")
    private String comment;

    @JsonBackReference
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER)
    private UserProfile userProfile;

    @JsonBackReference
    @ManyToMany(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "join_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @JsonBackReference
    @ManyToMany(
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "join_user_bands",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id"))
    private Collection<Band> bands;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Collection<Playlist> playlists;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "author")
    private Collection<Article> articles;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "id")
    private Collection<Comment> comments;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Collection<Like> likes;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLockedOut();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isApproved();
    }

}

