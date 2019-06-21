package ru.geekbrains.musicportal.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleDto implements Serializable {

    private Long id;
    private String title;
    private String shortDescription;
    private String content;
}
