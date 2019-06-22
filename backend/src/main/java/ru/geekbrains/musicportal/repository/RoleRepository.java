package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.user.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

    @Query("select r from Role r where r.name = :roleName")
    Role findOneByName(@Param("roleName") String roleName);

}
