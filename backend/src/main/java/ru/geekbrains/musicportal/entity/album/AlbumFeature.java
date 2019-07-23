package ru.geekbrains.musicportal.entity.album;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_album_features")
@EqualsAndHashCode(callSuper = true)
public class AlbumFeature extends AbstractEntity {

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_feature_type_id")
    private AlbumFeatureType albumFeatureType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;
}
