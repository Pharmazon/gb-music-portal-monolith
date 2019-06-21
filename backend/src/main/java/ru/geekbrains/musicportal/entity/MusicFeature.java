package ru.geekbrains.musicportal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "MUSIC_FEATURE")
public class MusicFeature extends AbstractEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRACK_ID", referencedColumnName = "ID")
    private Track track;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MUSIC_FEATURE_TYPE_ID", referencedColumnName = "ID")
    private MusicFeatureType musicFeatureType;
}
