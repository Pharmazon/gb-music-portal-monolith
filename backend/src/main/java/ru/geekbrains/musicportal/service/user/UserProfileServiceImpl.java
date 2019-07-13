package ru.geekbrains.musicportal.service.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.user.UserProfileDto;
import ru.geekbrains.musicportal.entity.user.UserProfile;
import ru.geekbrains.musicportal.repository.UserProfileRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private UserProfileRepository userProfileRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository,
                                  ModelMapper modelMapper) {
        this.userProfileRepository = userProfileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProfile saveOrUpdate(UserProfile entity) {
        return userProfileRepository.save(entity);
    }

    @Override
    public Optional<UserProfile> findOneEntityById(Long id) {
        return userProfileRepository.findById(id);
    }

    @Override
    public Collection<UserProfileDto> findAllDto() {
        return null;
    }

    @Override
    public UserProfileDto findOneDtoById(Long id) {
        return userProfileRepository.findOneById(id);
    }

    @Override
    public UserProfile convertToEntity(UserProfileDto dto) {
        return modelMapper.map(dto, UserProfile.class);
    }
}
