package ru.geekbrains.musicportal.service.storage;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import ru.geekbrains.musicportal.entity.track.Track;

import java.io.FileNotFoundException;


/**
 * Интерфейс для работы с файловым хранилищем
 */
public interface TrackStorage {

    ResponseEntity<InputStreamResource> getTrackFile(Track track) throws FileNotFoundException;
}
