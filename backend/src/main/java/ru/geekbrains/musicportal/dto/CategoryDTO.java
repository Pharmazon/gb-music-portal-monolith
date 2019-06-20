package ru.geekbrains.musicportal.dto;

import lombok.Data;
import ru.geekbrains.musicportal.entity.database.Category;

@Data
public class CategoryDTO {
    private Long id;
    private String title;

    public void fillByEntity(Category category){
        id = category.getId();
        title = category.getTitle();
    }
}
