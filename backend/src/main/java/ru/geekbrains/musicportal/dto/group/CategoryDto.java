package ru.geekbrains.musicportal.dto.group;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.group.Category;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDto extends AbstractDto {

    private CategoryDto parent;

    public CategoryDto(Category category) {
        super.setId(category.getId());
        super.setName(category.getName());
        super.setDescription(category.getDescription());
        parent = new CategoryDto(category.getParent());
    }

}
