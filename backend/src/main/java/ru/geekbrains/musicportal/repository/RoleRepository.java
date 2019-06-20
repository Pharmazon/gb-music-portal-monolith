package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import ru.geekbrains.musicportal.entity.security.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {
    @Query(value = "select r from Role r where r.loweredRoleName = :roleName")
    Role findOneByName(@Param("roleName")String roleName);
}
