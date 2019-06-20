package ru.geekbrains.musicportal.entity.database;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "track")
@Data
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "track_group",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> artists;

    @ManyToMany
    @JoinTable(
            name = "track_group",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Category> categories;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "track_id")
    private List<MusicFeature> metaInf;

    @Column(name = "link")
    private String fileLink;

    public void addCategory(Category category){

        if (!categories.contains(category)){
            categories.add(category);
        }
    }

    public void removeCategory(Category category){
            categories.remove(category);
    }

    public void addArtist(Group group){
        if (!artists.contains(group)){
            artists.add(group);
        }
    }

    public void removeArtist(Group group){
            artists.remove(group);
    }

    public void addFeature(MusicFeature feature){
        if (!metaInf.contains(feature)){
            metaInf.add(feature);
        }
    }

    public void removeFeature(MusicFeature feature){
            metaInf.remove(feature);

    }

    public MusicFeature getFeature(MusicFeatureType featureType){
        for (MusicFeature musicFeature: metaInf) {
           if (musicFeature.getMusicFeatureType().equals(featureType)){
               return musicFeature;
           }
        }
        return null;
    }

}
