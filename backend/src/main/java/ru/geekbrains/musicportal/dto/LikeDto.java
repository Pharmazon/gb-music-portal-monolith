package ru.geekbrains.musicportal.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.blog.Like;

@Data
@NoArgsConstructor
@EqualsAndHashCode (callSuper = true)
public class LikeDto extends AbstractDto {

    private UserDto user;

    private Long entityId;

    private String entity;

    public LikeDto(Like like) {
        super.setId(like.getId());
        user = new UserDto(like.getUser());
        entityId = like.getLikedEntity();
        entity = like.getTypeLikedEntity();
    }
}
