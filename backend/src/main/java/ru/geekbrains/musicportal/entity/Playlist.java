package ru.geekbrains.musicportal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PLAYLIST")
public class Playlist extends AbstractEntity {

    @Column(name = "TITLE")
    private String title;

    @OneToMany(mappedBy = "playList")
    private List<TrackInPlaylist> tracksInPlaylist;


}
