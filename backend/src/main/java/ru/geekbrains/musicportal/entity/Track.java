package ru.geekbrains.musicportal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TRACK")
public class Track extends AbstractEntity{

    @Column(name = "TITLE")
    private String title;

    @Column(name = "FILE_LINK")
    private String fileLink;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "track", cascade = CascadeType.ALL)
    private List<MusicFeature> musicFeatureList;

    @ManyToMany(mappedBy = "TRACK")
    private List<ArtGroup> artGroups;

}
