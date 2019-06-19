package ru.geekbrains.musicportal.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ROLES")
public class Role extends AbstractEntity{
    @Column(name = "ROLE_NAME", nullable = false)
    private String roleName;
    @Column(name = "DEFINITION")
    private String definition;
}
