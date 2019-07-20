package ru.geekbrains.musicportal.service.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.entity.track.Track;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Класс реализует интерфейс для работы с файловым хранилищем в виде директории на сервере
 */
@Service
public class TrackStorageFolder implements TrackStorage {

    // В application.yml прописали путь папке-хранилищу
    @Value("${storage.folder.path}")
    private String folder;

    @Override
    public ResponseEntity<InputStreamResource> getTrackFile(Track track) throws FileNotFoundException {
        File file = new File(folder + File.separator + track.getFileLink());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(file.length())
                .body(resource);
    }
}
