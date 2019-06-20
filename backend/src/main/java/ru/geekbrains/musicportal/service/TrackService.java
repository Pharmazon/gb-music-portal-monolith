package ru.geekbrains.musicportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.entity.database.Track;
import ru.geekbrains.musicportal.repository.TrackRepository;

@Service
public class TrackService {
    @Autowired
    private TrackRepository trackRepository;

    public Track getPlaylistById(Long id){
        return trackRepository.getById(id);
    }
}
