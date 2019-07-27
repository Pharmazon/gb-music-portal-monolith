package ru.geekbrains.musicportal.dto.like;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.like.Like;
import ru.geekbrains.musicportal.enums.EntityLikeEnum;
import ru.geekbrains.musicportal.marker.LikeViews;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LikeDto implements Serializable {

    @JsonView(LikeViews.All.class)
    private Long id;

    @JsonView(LikeViews.All.class)
    private LocalDateTime creationDate;

    @JsonView(LikeViews.All.class)
    private Long userId;

    @JsonView(LikeViews.All.class)
    private Long entityId;

    @JsonView(LikeViews.All.class)
    private EntityLikeEnum entity;

    public LikeDto(Like like) {
        id = like.getId();
        creationDate = like.getCreationDate();
        userId = like.getUser().getId();
        entityId = like.getLikedEntity();
        entity = like.getTypeLikedEntity();
    }
}
