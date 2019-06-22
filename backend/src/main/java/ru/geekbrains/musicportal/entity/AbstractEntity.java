package ru.geekbrains.musicportal.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractEntity {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "MUSIC_PORTAL_SEQUENCE", allocationSize = 1)
    @GeneratedValue(generator = "seq_name", strategy = GenerationType.SEQUENCE)
    protected Long id;

    @Column(name = "LAST_UPDATE", nullable = false)
    private Date lastUpdate;

    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;


}