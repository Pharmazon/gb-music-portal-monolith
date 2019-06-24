package ru.geekbrains.musicportal.entity.track;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_music_group")
@EqualsAndHashCode(callSuper = true)
public class MusicGroup extends AbstractEntity {

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "musicGroup")
    private Collection<User> participants;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "musicGroup")
    private Collection<Track> tracks;
}
