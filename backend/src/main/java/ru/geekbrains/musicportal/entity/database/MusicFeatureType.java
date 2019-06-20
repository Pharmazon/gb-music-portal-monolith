package ru.geekbrains.musicportal.entity.database;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "musicfeaturetype")
@Data
public class MusicFeatureType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
}
