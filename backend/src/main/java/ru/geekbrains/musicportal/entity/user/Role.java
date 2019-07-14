package ru.geekbrains.musicportal.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.enums.UserRoleEnum;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_roles")
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractEntity implements GrantedAuthority {

    public Role(UserRoleEnum roleEnum) {
        super.setName(roleEnum.getName());
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
