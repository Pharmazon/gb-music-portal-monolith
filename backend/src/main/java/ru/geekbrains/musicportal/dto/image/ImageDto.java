package ru.geekbrains.musicportal.dto.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.image.Image;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ImageDto extends AbstractDto {

    private String imgLink;

    public ImageDto(Image image) {
        if (image == null) return;

        super.setId(image.getId());
        super.setName(image.getName());
        super.setDescription(image.getDescription());
        imgLink = image.getImgLink();
    }
}
