package ru.geekbrains.musicportal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "mp_engines")
public class EngineEntity extends AbstractEntity {

    @Column(name = "number", unique = true)
    private String number;

    @OneToMany(mappedBy = "engine", fetch = FetchType.LAZY)
    private List<CarEntity> cars;

}
