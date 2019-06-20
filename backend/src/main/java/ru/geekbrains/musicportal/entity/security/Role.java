package ru.geekbrains.musicportal.entity.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role {

    public Role(){
        users = new ArrayList<>();
    }

    @Getter
    @Setter
    @Id
    @Column(name = "role_id")
    private UUID roleID;

    @Getter
    @Setter
    @Column(name = "role_name")
    private String roleName;

    @Getter
    @Setter
    @Column(name = "lowered_role_name")
    private String loweredRoleName;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "users_in_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> users;

    public Collection<User> getUsers(){
        return users;
    }
}
