package ru.geekbrains.musicportal.entity.music;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_music_feature_type")
@EqualsAndHashCode(callSuper = true)
public class MusicFeatureType extends AbstractEntity {

    @OneToMany(
            mappedBy = "musicFeatureType",
            fetch = FetchType.LAZY)
    private Collection<MusicFeature> musicFeatures;

}
