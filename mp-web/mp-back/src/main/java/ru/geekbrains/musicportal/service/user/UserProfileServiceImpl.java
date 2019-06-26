package ru.geekbrains.musicportal.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.entity.user.UserProfile;
import ru.geekbrains.musicportal.repository.UserProfileRepository;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private UserProfileRepository userProfileRepository;

    @Autowired
    public void setUserRepository(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile save(UserProfile entity) {
        return userProfileRepository.save(entity);
    }

    @Override
    public Optional<UserProfile> findById(Long id) {
        return userProfileRepository.findById(id);
    }
}
