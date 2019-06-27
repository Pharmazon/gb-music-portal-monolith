package ru.geekbrains.musicportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.user.Role;
import ru.geekbrains.musicportal.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_likes")
@EqualsAndHashCode(callSuper = true)
public class Like extends AbstractEntity {

    @Column(name = "create_date")
    private LocalDateTime creationDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Collection<User> user;

    @Column(name = "entity")
    private String typeLikedEntity;

    @Column (name = "entity_id")
    private Long likedEntity;

}
