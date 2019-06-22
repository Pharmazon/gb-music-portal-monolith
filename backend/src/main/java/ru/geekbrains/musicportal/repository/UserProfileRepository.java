package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.user.UserProfile;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, String> {
}
