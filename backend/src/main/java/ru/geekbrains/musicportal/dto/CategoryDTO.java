package ru.geekbrains.musicportal.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.group.Category;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDto extends AbstractDto {

    public CategoryDto(Category category) {
        super.setId(category.getId());
        super.setName(category.getName());
        super.setDescription(category.getDescription());
    }

}
