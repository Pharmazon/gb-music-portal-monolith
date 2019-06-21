package ru.geekbrains.musicportal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "MUSIC_FEATURE_TYPE")
public class MusicFeatureType extends AbstractEntity {

    @Column(name = "TITLE")
    private String title;

}
