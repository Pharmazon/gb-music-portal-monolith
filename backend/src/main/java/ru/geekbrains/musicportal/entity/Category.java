package ru.geekbrains.musicportal.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CATEGORY")
public class Category extends AbstractEntity {
    @Column(name = "TITLE")
    private String title;
}
