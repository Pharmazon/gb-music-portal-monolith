package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.user.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

    Role findOneByName(String name);

}
