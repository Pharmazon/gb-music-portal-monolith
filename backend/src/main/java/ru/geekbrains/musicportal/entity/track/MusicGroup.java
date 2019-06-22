package ru.geekbrains.musicportal.entity.track;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.user.User;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_music_group")
@EqualsAndHashCode(callSuper = true)
public class MusicGroup extends AbstractEntity {

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "group")
    private Collection<User> participants;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "group")
    private Collection<Track> tracks;
}
