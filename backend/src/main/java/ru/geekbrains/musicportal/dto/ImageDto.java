package ru.geekbrains.musicportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.image.Image;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ImageDto extends AbstractDto {
    private String imgLink;

    public ImageDto(Image image) {
        super.setId(image.getId());
        super.setName(image.getName());
        super.setDescription(image.getDescription());
        imgLink = image.getImgLink();
    }
}
