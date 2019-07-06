package ru.geekbrains.musicportal.entity.images;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.user.User;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Image extends AbstractEntity {
    @Column
    private String imgLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
