package ru.geekbrains.musicportal.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArticleDto extends AbstractDto {

    private String shortDescripton;

    private String articleContent;
}
