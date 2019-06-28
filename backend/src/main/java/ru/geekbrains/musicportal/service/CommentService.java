package ru.geekbrains.musicportal.service;

import ru.geekbrains.musicportal.entity.Comment;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface CommentService extends CommonService<Comment> {

    void deleteById(Long id);
}
