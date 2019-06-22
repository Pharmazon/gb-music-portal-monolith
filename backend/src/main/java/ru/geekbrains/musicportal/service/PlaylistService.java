package ru.geekbrains.musicportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.entity.Playlist;
import ru.geekbrains.musicportal.repository.PlaylistRepository;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    public Playlist getPlaylistById(Long id){
        return playlistRepository.findOneById(id);
    }

    public Playlist savePlaylist(Playlist playlist){
        Playlist categoryResult = playlistRepository.save(playlist);
        return categoryResult;
    }

    public void deleteCategory(Playlist playlist){
        playlistRepository.delete(playlist);
    }
}
