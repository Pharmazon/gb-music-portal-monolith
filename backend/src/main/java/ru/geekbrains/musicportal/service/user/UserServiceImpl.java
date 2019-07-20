package ru.geekbrains.musicportal.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.musicportal.dto.user.RoleDto;
import ru.geekbrains.musicportal.dto.user.UserDto;
import ru.geekbrains.musicportal.dto.user.UserRegistrationDto;
import ru.geekbrains.musicportal.entity.user.Role;
import ru.geekbrains.musicportal.entity.user.User;
import ru.geekbrains.musicportal.enums.UserRoleEnum;
import ru.geekbrains.musicportal.exception.UserAlreadyExistsException;
import ru.geekbrains.musicportal.message.UserMessage;
import ru.geekbrains.musicportal.repository.RoleRepository;
import ru.geekbrains.musicportal.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = encoder;
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException(UserMessage.INVALID_USERNAME.getText());
        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                mapRolesToAuthorities(user.getRoles()));
        return user;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public User getOneById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void deleteOne(User user) {
        userRepository.deleteById(user.getId());
    }

    public boolean changePassword(String username, String oldPsw, String newPsw) {
        boolean result = true;
        User user = userRepository.findOneByUsername(username);
        if(user == null || !passwordEncoder.matches(oldPsw, user.getPassword())) return false;
        user.setPassword(passwordEncoder.encode(newPsw));
        user.setLastPasswordChangeDate(LocalDateTime.now());
        save(user);
        return result;
    }

    public boolean changePasswordByPasswordAnswer(String username, String newPsw, String passwordAnswer) {
        boolean res = true;
        User user = userRepository.findOneByUsername(username);
        if(user == null || !passwordEncoder.matches(user.getPasswordAnswer(), passwordAnswer)) return false;
        user.setPassword(passwordEncoder.encode(newPsw));
        user.setLastPasswordChangeDate(LocalDateTime.now());
        save(user);
        return res;
    }

    @Override
    public boolean isExistsByName(String username) {
        User fromDb = findByUserName(username);
        return fromDb != null;
    }

    @Override
    public UserDto getOneDtoById(Long id) {
        return userRepository.findOneById(id);
    }

    @Override
    public void deleteOneById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getDtoByUsername(String username) {
        return userRepository.getDtoByUsername(username);
    }

    /**
     * Более простой вариант сохранения пользователя в большенстве реализаций подобный вариант,
     * если роли будут выбиратся при регестрации пользователем можно брать их из дто.
     */
    @Override
    @Transactional
    public User save(UserRegistrationDto userRegistrationDto) throws UserAlreadyExistsException {
        String username = userRegistrationDto.getUsername();
        if (isExistsByName(userRegistrationDto.getUsername())) {
            throw new UserAlreadyExistsException(username);
        }

        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setEmail(userRegistrationDto.getEmail());
        user.setLastPasswordChangeDate(LocalDateTime.now());
        user.setLastLoginDate(LocalDateTime.now());
        Collection<Role> roles = getRolesFromEnum(UserRoleEnum.getUser());
        user.setRoles(roles);
        user.setPasswordQuestion(userRegistrationDto.getPasswordQuestion());
        user.setPasswordAnswer(userRegistrationDto.getPasswordAnswer());
        user.setApproved(userRegistrationDto.isApproved());
        user = save(user);
        return user;
    }

    @Override
    @Transactional
    public void registerUser(
            String username,
            String password,
            String email,
            Collection<UserRoleEnum> rolesEnum) throws UserAlreadyExistsException {
        if (isExistsByName(username)) {
            throw new UserAlreadyExistsException(username);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        Collection<Role> roles = getRolesFromEnum(rolesEnum);
        user.setRoles(roles);
        save(user);
    }

    @Transactional
    public User saveOrUpdate(UserDto userDto) {
        String username = userDto.getUsername();

        User user = userRepository.findOneByUsername(username);
        if (user.getId().equals(userDto.getId()) && user.getUsername().equals(username)) {
            user.setId(userDto.getId());
        }

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setLastUpdate(LocalDateTime.now());
        ArrayList<Role> roles = new ArrayList<>();

        for (RoleDto roleDto:userDto.getRoles()) {
            roles.add(roleRepository.findOneByName(roleDto.getName()));
        }

        user.setRoles(roles);
        user.setPasswordQuestion(userDto.getPasswordQuestion());
        user.setPasswordAnswer(userDto.getPasswordAnswer());
        user.setApproved(userDto.isApproved());
        user = save(user);
        return user;
    }

    /**
     * Конвертирует enum ролей в список ролей из БД
     * @param roleEnums - коллекция enum ролей
     * @return - коллекция ролей из базы
     */
    private Collection<Role> getRolesFromEnum(Collection<UserRoleEnum> roleEnums) {
        List<Role> roles = new ArrayList<>();
        for (UserRoleEnum roleEnum : roleEnums) {
            Role fromDb = roleRepository.findOneByName(roleEnum.getName());
            roles.add(fromDb);
        }
        return roles;
    }
}
