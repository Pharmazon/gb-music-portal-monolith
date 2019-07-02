package ru.geekbrains.musicportal.entity.track;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.group.Category;
import ru.geekbrains.musicportal.entity.user.Band;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_track")
@EqualsAndHashCode(callSuper = true)
public class Track extends AbstractEntity {

    @Column(name = "link")
    private String fileLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_group_id")
    private Band band;

    @ManyToMany
    @JoinTable(
            name = "join_track_category",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Collection<Category> categories;

    @OneToMany(
            mappedBy = "track",
            fetch = FetchType.LAZY)
    private Collection<TrackFeature> trackFeatures;

    @OneToMany(
            mappedBy = "track",
            fetch = FetchType.LAZY)
    private Collection<PlaylistTrack> playlistTracks;

}
