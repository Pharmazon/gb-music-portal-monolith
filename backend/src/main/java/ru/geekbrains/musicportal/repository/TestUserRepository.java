package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.TestUser;

import java.util.UUID;

/**
 * Временный репозиторий нужно акктуализировать с финальной версией.
 */
@Repository
public interface TestUserRepository extends CrudRepository<TestUser, UUID> {
    TestUser findByEmail(String email);
    TestUser fondByName(String testUserName);
}
