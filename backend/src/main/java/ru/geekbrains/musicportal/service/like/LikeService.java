package ru.geekbrains.musicportal.service.like;

import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.entity.blog.Like;
import ru.geekbrains.musicportal.dto.TrackDto;
import ru.geekbrains.musicportal.service.common.CommonService;

import java.util.List;

@Service
public interface LikeService extends CommonService<Like> {
}
