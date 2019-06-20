package ru.geekbrains.musicportal.entities;

import lombok.Data;
import ru.geekbrains.musicportal.entities.database.Category;
import ru.geekbrains.musicportal.entities.database.MusicFeatureType;
import ru.geekbrains.musicportal.entities.database.PlaylistFeature;
import ru.geekbrains.musicportal.entities.dto.CategoryDTO;
import ru.geekbrains.musicportal.entities.dto.PlaylistFeatureDTO;
import ru.geekbrains.musicportal.entities.dto.TrackDTO;
import ru.geekbrains.musicportal.entities.dto.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class PlaylistObject {
    private Long id;
    private String title;
    private UserDTO owner;
    private ArrayList<TrackDTO> tracks;
    private ArrayList<CategoryDTO> categories;
    private ArrayList<PlaylistFeatureDTO> features;
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

    public void addCategory(CategoryDTO category){
        if (categories == null){
            categories = new ArrayList<>();
        }

        if (!categories.contains(category)){
            categories.add(category);
        }
    }

    public void removeCategory(CategoryDTO track){
        if (categories != null){
            categories.remove(track);
        }
    }

    public void addFeature(PlaylistFeatureDTO feature){
        if (features == null){
            features = new ArrayList<>();
        }

        if (!features.contains(feature)){
            features.add(feature);
        }
    }

    public void removeFeature(PlaylistFeatureDTO feature){
        if (features != null){
            features.remove(feature);
        }
    }

    public PlaylistFeatureDTO getFeature(MusicFeatureType featureType){
        for (PlaylistFeatureDTO playlistFeature: features) {
            if (playlistFeature.getPlaylistFeatureType().equals(featureType)){
                return playlistFeature;
            }
        }
        return null;
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

    public HashMap<CategoryDTO,Integer> getCategoryStatistics(){
        HashMap<CategoryDTO,Integer> resultMap = new HashMap<>();
        for (TrackDTO track: tracks) {
            ArrayList<CategoryDTO> trackCategories = track.getCategories();

            for (CategoryDTO category: categories) {
                int stat = resultMap.get(category);
                stat = stat > 0 ? stat : 0;
                resultMap.put(category,stat+1);
            }
        }

        return resultMap;
    }

}
