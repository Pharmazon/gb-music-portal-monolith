package ru.geekbrains.musicportal.entity.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.geekbrains.musicportal.enums.UserRoleEnum;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    public Role(UserRoleEnum roleEnum) {
        name = roleEnum.getName();
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
