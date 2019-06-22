package ru.geekbrains.musicportal.entity.music;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.AbstractEntity;
import ru.geekbrains.musicportal.entity.track.Track;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_music_feature")
@EqualsAndHashCode(callSuper = true)
public class MusicFeature extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    private Track track;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_feature_type_id")
    private MusicFeatureType musicFeatureType;

}
