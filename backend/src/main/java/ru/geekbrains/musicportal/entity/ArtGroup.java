package ru.geekbrains.musicportal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ART_GROUP")
public class ArtGroup extends AbstractEntity{
    @Column(name = "TITLE")
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "GROUP_ARTISTS",
            joinColumns = {@JoinColumn(name = "GROUP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ARTIST_ID")}
    )
    private List<User> artistList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LEADER_ID", referencedColumnName = "ID")
    private User groupLeader;

    @ManyToMany
    @JoinTable(name = "ART_GROUP_TRACK",
        joinColumns = {@JoinColumn(name = "ART_GROUP_ID")},
        inverseJoinColumns = {@JoinColumn(name = "TRACK_ID")}
    )
    private List<Track> trackList;

}
