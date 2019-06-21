package ru.geekbrains.musicportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.entity.Track;
import ru.geekbrains.musicportal.repository.TrackRepository;

@Service
public class TrackService {
    @Autowired
    private TrackRepository trackRepository;

    public Track getPlaylistById(Long id){
        return trackRepository.findOneById(id);
    }

    public Track savePlaylist(Track track){
        Track categoryResult = trackRepository.save(track);
        return categoryResult;
    }

    public void deleteCategory(Track track){
        trackRepository.delete(track);
    }
}
