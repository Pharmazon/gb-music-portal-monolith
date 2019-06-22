package ru.geekbrains.musicportal.entity.group;

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
@Table(name = "app_categories")
@EqualsAndHashCode(callSuper = true)
public class Category extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "parent")
    private Category parent;

    @ManyToMany
    @JoinTable(
            name = "join_track_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id")
    )
    private Collection<Track> tracks;

}
