package ru.geekbrains.musicportal.controller.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.service.storage.TrackStorage;
import ru.geekbrains.musicportal.service.track.TrackService;

import java.io.FileNotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/api/miraculous/tracks")
public class PlayerController {

    private TrackService trackService;
    private TrackStorage storage;

    @Autowired
    public PlayerController(TrackService trackService, TrackStorage storage) {
        this.trackService = trackService;
        this.storage = storage;
    }

    /**
     * Контроллер возвращает содержимое аудиофайла по его идентификатору.
     * @param id Идентифткатор трека в базе
     * @return Возвращает тело файла. Если трека нет в базе, то выдаёт ответ 404 (HttpStatus.NOT_FOUND), если не удалось
     * вытащить файл, то ответ 500 (HttpStatus.INTERNAL_SERVER_ERROR)
     */
    @GetMapping(value = "/play/{id}", produces = "audio/mpeg")
    public ResponseEntity<InputStreamResource> getPlayFile(@PathVariable("id") Long id) {
        Track track = trackService.findTrackById(id);
        if (track == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        try {
            return storage.getTrackFile(track);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}