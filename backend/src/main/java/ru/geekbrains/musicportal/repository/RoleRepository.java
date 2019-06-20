package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import ru.geekbrains.musicportal.entity.security.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {
    Role findOneByName(String roleName);
}
