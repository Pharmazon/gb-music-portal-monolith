package ru.geekbrains.musicportal.entity;

import lombok.Data;
import ru.geekbrains.musicportal.entity.database.*;
import ru.geekbrains.musicportal.dto.CategoryDTO;
import ru.geekbrains.musicportal.dto.PlaylistFeatureDTO;
import ru.geekbrains.musicportal.dto.TrackDTO;
import ru.geekbrains.musicportal.dto.UserDTO;
import ru.geekbrains.musicportal.entity.security.User;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class PlaylistObject {
    private Long id;
    private String title;
    private UserDTO owner;
    private ArrayList<TrackDTO> tracks;
    private HashMap<String,Integer> categories;
    private HashMap<String,String> features;
    private int currentTrack = 0;

    public void addTrack(TrackDTO track){
        if (tracks == null){
            tracks = new ArrayList<>();
        }

        if (!tracks.contains(track)){
            tracks.add(track);
        }
    }

    public void removeTrack(TrackDTO track){
        if (tracks != null){
            tracks.remove(track);
        }
    }

    public void addCategory(String categoryName){
        if (categories == null){
            categories = new HashMap<>();
        }
        if (categories.containsKey(categoryName)){
            categories.put(categoryName,categories.get(categoryName)+1);
        }else{
            categories.put(categoryName,1);
        }

    }

    public void removeCategory(String categoryName){
        if (categories != null){
            categories.remove(categoryName);
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

    public TrackDTO next(){
        if (currentTrack + 1 == tracks.size()){
            currentTrack = 0;
        }else{
            currentTrack = currentTrack +1;
        }
        return tracks.get(currentTrack);
    }

    public TrackDTO previous(){
        if (tracks != null && tracks.size() != 0){
            if (currentTrack == 0){
                currentTrack = tracks.size()-1;
            }else{
                currentTrack = currentTrack -1;
            }
            return tracks.get(currentTrack);
        }
    return null;
    }

    public void fillByEntity(Playlist playlist){
        id = playlist.getId();
        title = playlist.getTitle();
        UserDTO user = new UserDTO();
        user.fillByEntity(playlist.getOwner());
        owner = user;

        if (tracks != null){
            tracks.clear();
        }

        if (categories != null){
            categories.clear();
        }

        ArrayList<Track> tracksTmp = (ArrayList<Track>) playlist.getTracks();
        for (Track track: tracksTmp) {
            TrackDTO trackDTO = new TrackDTO();
            trackDTO.fillByEntity(track);
            addTrack(trackDTO);

            ArrayList<Category> categoriesTmp = (ArrayList<Category>) track.getCategories();
            for (Category category: categoriesTmp) {
                String categoryName = category.getTitle();
                addCategory(categoryName);
            }

        }

        if (features != null){
            features.clear();
        }

        ArrayList<PlaylistFeature> featuresTmp = (ArrayList<PlaylistFeature>) playlist.getFeatures();
        for (PlaylistFeature feature: featuresTmp) {
            addFeature(feature.getPlaylistFeatureType().getTitle(),feature.getValue());
        }

    }

}
