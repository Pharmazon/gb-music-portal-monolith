package ru.geekbrains.musicportal.dto;

import lombok.Data;
import ru.geekbrains.musicportal.entity.database.Category;
import ru.geekbrains.musicportal.entity.database.Group;
import ru.geekbrains.musicportal.entity.database.MusicFeature;
import ru.geekbrains.musicportal.entity.database.Track;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class TrackDTO {
    private Long id;
    private String title;
    private ArrayList<GroupDTO> artists;
    private ArrayList<String> categories;
    private HashMap<String,String> features;
    private String fileLink;

    public void addCategory(String categoryName){
        if (categories == null){
            categories = new ArrayList<>();
        }

        if (!categories.contains(categoryName)){
            categories.add(categoryName);
        }
    }

    public void removeCategory(String categoryName){
        if (categories != null){
            categories.remove(categoryName);
        }
    }

    public void addArtist(GroupDTO group){
        if (artists == null){
            artists = new ArrayList<>();
        }

        if (!artists.contains(group)){
            artists.add(group);
        }
    }

    public void removeArtist(GroupDTO group){
        if (artists != null){
            artists.remove(group);
        }
    }

    public void addFeature(String featureName, String value){
        if (features == null){
            features = new HashMap<>();
        }
        features.put(featureName,value);
    }

    public void removeFeature(String featureName){
        if (features != null){
            features.remove(featureName);
        }
    }

    public String getFeature(String featureName){
        return features.get(featureName);
    }

    public void fillByEntity(Track track){
        id = track.getId();
        title = track.getTitle();

        if (artists != null){
            artists.clear();
        }

        ArrayList<Group> groupsTmp = (ArrayList<Group>) track.getArtists();
        for (Group group: groupsTmp) {
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.fillByEntity(group);
            addArtist(groupDTO);
        }

        ArrayList<Category> categoriesTmp = (ArrayList<Category>) track.getCategories();
        for (Category category: categoriesTmp) {
            addCategory(category.getTitle());
        }

        ArrayList<MusicFeature> featuresTmp = (ArrayList<MusicFeature>) track.getMetaInf();
        for (MusicFeature feature: featuresTmp) {
            addFeature(feature.getMusicFeatureType().getTitle(),feature.getValue());
        }
    }
}
