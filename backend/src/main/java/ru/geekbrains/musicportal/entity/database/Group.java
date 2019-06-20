package ru.geekbrains.musicportal.entity.database;

import lombok.Data;
import ru.geekbrains.musicportal.entity.security.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group")
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "group_id")
    private List<User> participants;
}