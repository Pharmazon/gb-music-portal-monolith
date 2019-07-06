package ru.geekbrains.musicportal.dto.blog;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArticleDto extends AbstractDto {

    private String shortDescripton;

    private String articleContent;
}
