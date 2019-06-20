package ru.geekbrains.musicportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.entity.database.Playlist;
import ru.geekbrains.musicportal.repository.PlaylistRepository;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    public Playlist getPlaylistById(Long id){
        return playlistRepository.getById(id);
    }

}
