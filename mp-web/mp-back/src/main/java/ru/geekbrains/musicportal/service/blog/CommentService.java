package ru.geekbrains.musicportal.service.blog;

import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface CommentService extends CommonService<Comment> {

    void deleteById(Long id);
}
