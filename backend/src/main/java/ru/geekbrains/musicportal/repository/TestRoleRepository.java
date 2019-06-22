package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.TestRole;

import java.util.UUID;

/**
 * Временный репозиторий нужно акктуализировать с финальной версией.
 */
@Repository
public interface TestRoleRepository extends CrudRepository<TestRole, UUID> {
    TestRole findOneByName(String testRoleName);
}
