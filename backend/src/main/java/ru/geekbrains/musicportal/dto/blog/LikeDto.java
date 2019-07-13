package ru.geekbrains.musicportal.dto.blog;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.dto.user.UserDto;
import ru.geekbrains.musicportal.entity.blog.Like;
import ru.geekbrains.musicportal.enums.EntityLikeEnum;

@Data
@NoArgsConstructor
@EqualsAndHashCode (callSuper = true)
public class LikeDto extends AbstractDto {

    private UserDto user;

    private Long entityId;

    private EntityLikeEnum entity;

    public LikeDto(Like like) {
        super.setId(like.getId());
        user = new UserDto(like.getUser());
        entityId = like.getLikedEntity();
        entity = like.getTypeLikedEntity();
    }
}
