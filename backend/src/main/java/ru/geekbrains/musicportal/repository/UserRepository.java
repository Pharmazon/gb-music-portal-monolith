package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.user.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    @Query(value = "select u from User u where u.username = :userName")
    User findByUserName(@Param("userName") String userName);

//    @Query(value = "select u from User u where u.loweredUserName = :userName and u.isApproved = 0 and u.isLockedOut = 0")
//    User findValidUser(@Param("userName") String userName);
}
