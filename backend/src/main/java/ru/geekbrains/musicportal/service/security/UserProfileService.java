package ru.geekbrains.musicportal.service.security;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.geekbrains.musicportal.dto.UserProfileDto;
import ru.geekbrains.musicportal.entity.security.UserProfile;
import ru.geekbrains.musicportal.repository.UserProfileRepository;

import java.util.UUID;

@Service
public class UserProfileService {
    private UserProfileRepository userProfileRepository;

    @Autowired
    public void setUserRepository(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public boolean save(UserProfileDto source) {
        if(source == null || source.getUserID() == null)
            return false;

        userProfileRepository.deleteById(source.getUserID());

        ModelMapper modelMapper = new ModelMapper();
        UserProfile userProfile = modelMapper.map(source, UserProfile.class);
        userProfileRepository.save(userProfile);

        return true;
    }

    public UserProfileDto get(UUID userId){
        UserProfileDto res = null;

        UserProfile userProfile = userProfileRepository.findById(userId).orElse(null);

        if(userProfile != null){
            ModelMapper modelMapper = new ModelMapper();
            res = modelMapper.map(userProfile, UserProfileDto.class);
        }

        return  res;
    }
}
