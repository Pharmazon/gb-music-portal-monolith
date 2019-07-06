package ru.geekbrains.musicportal.entity.image;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "app_images")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Image extends AbstractEntity {

    @Column
    private String imgLink;

}
