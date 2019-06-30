package ru.geekbrains.musicportal.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.track.Track;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_music_group")
@EqualsAndHashCode(callSuper = true)
public class MusicGroup extends AbstractEntity {

    @ManyToMany(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "join_user_music_groups",
            joinColumns = @JoinColumn(name = "music_group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> users;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "musicGroup")
    private Collection<Track> tracks;
}
