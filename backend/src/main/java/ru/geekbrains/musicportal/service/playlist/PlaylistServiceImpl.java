package ru.geekbrains.musicportal.service.playlist;

import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.entity.track.PlaylistTrack;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.repository.PlaylistRepository;
import ru.geekbrains.musicportal.repository.TrackRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistRepository playlistRepository;
    private TrackRepository trackRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository,
                               TrackRepository trackRepository,
                               ModelMapper modelMapper) {
        this.playlistRepository = playlistRepository;
        this.modelMapper = modelMapper;
        this.trackRepository = trackRepository;
    }

    @Override
    public Playlist saveOrUpdate(Playlist entity) {
        return playlistRepository.save(entity);
    }

    @Override
    public Optional<Playlist> findOneEntityById(Long id) {
        return playlistRepository.findById(id);
    }

    @Override
    public Collection<PlaylistDto> findAllDtos() {
        return playlistRepository.findAllByIdNotNull();
    }

    @Override
    public Collection<Playlist> findAll() {
        return (Collection<Playlist>) playlistRepository.findAll();
    }

    @Override
    public PlaylistDto findOneDtoById(Long id) {
        return playlistRepository.findOneById(id);
    }

    @Override
    public Playlist convertToEntity(PlaylistDto dto) {
        return modelMapper.map(dto, Playlist.class);
    }

    @Override
    public boolean deleteById(Long id) {
        playlistRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<Playlist> getPlaylistsWithPagingAndFiltering(int pageNumber,
                                                             int pageSize,
                                                             Specification<Playlist> specification) {
        return playlistRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Collection<PlaylistDto> getTop(int max) {
        Collection<Playlist> top = playlistRepository.getTop(max);
        return top.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<PlaylistDto> getAllByUserId(Long id) {
        return playlistRepository.findAllByUser_Id(id);
    }

    @Override
    public Collection<PlaylistDto> findAllByGenreName(String name) {
        return playlistRepository.findAllByGenreName(name);
    }

    @Transactional
    @Override
    public PlaylistDto addTrackToPlaylist(Long playlistId, Long trackId) {
        Optional<Track> optionalTrack = trackRepository.findById(trackId);
        if (!optionalTrack.isPresent()) return null;
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        if (!optionalPlaylist.isPresent()) return null;
        Playlist playlist = optionalPlaylist.get();
        Track track = optionalTrack.get();
        Collection<PlaylistTrack> playlistTracks = playlist.getPlaylistTracks();
        PlaylistTrack playlistTrack = new PlaylistTrack(getNextTrackPosition(playlist), playlist, track);
        playlistTracks.add(playlistTrack);
        //TODO:Сохранить в базу
        return convertToDto(playlist);
    }

    @Transactional
    @Override
    public PlaylistDto deleteTrackFromPlaylist(Long playlistId, Long trackId) {
        Optional<Track> optionalTrack = trackRepository.findById(trackId);
        if (!optionalTrack.isPresent()) return null;
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        if (!optionalPlaylist.isPresent()) return null;
        Playlist playlist = optionalPlaylist.get();
        Track track = optionalTrack.get();
        List<PlaylistTrack> playlistTracks = (List<PlaylistTrack>) playlist.getPlaylistTracks();
        for (PlaylistTrack playlistTrack : playlistTracks) {
            if (playlistTrack.getTrack().equals(track)) {
                playlistTracks.remove(playlistTrack);
                break;
            }
        }
        //TODO:Сохранить в базу
        return convertToDto(playlist);
    }

    private int getNextTrackPosition(Playlist playlist) {
        int max = 0;
        Collection<PlaylistTrack> playlistTracks = playlist.getPlaylistTracks();
        for (PlaylistTrack playlistTrack : playlistTracks) {
            @NonNull Integer position = playlistTrack.getPosition();
            if (position > max) max = position;
        }
        return max + 1;
    }

    @Override
    public PlaylistDto convertToDto(Playlist entity) {
        return modelMapper.map(entity, PlaylistDto.class);
    }
}
