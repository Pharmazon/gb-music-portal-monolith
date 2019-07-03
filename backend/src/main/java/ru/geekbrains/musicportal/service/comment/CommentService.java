package ru.geekbrains.musicportal.service.comment;

import ru.geekbrains.musicportal.dto.blog.CommentDto;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface CommentService extends CommonService<Comment, CommentDto> {

    void deleteById(Long id);
}
