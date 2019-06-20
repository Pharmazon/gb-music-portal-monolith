package ru.geekbrains.musicportal.entity.database;

import lombok.Data;
import ru.geekbrains.musicportal.entity.security.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "parent")
    private User owner;

    @ManyToMany
    @JoinTable(
            name = "playlist_track",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id")
    )
    private List<Track> tracks;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "playlist_id")
    private List<PlaylistFeature> features;


    public void addTrack(Track track){
        if (!tracks.contains(track)){
            tracks.add(track);
        }
    }

    public void removeTrack(Track track){
        if (tracks != null){
            tracks.remove(track);
        }
    }

    public void addFeature(PlaylistFeature feature){
        if (!features.contains(feature)){
            features.add(feature);
        }
    }

    public void removeFeature(PlaylistFeature feature){
            features.remove(feature);
    }

    public PlaylistFeature getFeature(MusicFeatureType featureType){
        for (PlaylistFeature playlistFeature: features) {
            if (playlistFeature.getPlaylistFeatureType().equals(featureType)){
                return playlistFeature;
            }
        }
        return null;
    }

}
