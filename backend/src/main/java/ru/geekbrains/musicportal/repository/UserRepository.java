package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.UserDto;
import ru.geekbrains.musicportal.entity.user.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    User findOneByUsername(String username);

    UserDto getDtoByUsername(String username);

}
