package ru.geekbrains.musicportal.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.musicportal.dto.UserDto;
import ru.geekbrains.musicportal.entity.user.Role;
import ru.geekbrains.musicportal.entity.user.User;
import ru.geekbrains.musicportal.enums.UserRoleEnum;
import ru.geekbrains.musicportal.exception.UserAlreadyExistException;
import ru.geekbrains.musicportal.repository.RoleRepository;
import ru.geekbrains.musicportal.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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
    @Transactional
    public User findByUserName(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(userName.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username");
        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                mapRolesToAuthorities(user.getRoles()));
        return user;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    /**
     * Закаментил этот метод ниже написал свой вариант попроще
     * @param user
     * @return
     */
//    //переписать метод целиком
//    @Override
//    @Transactional
//    public User save(UserDto dto) {
//        User entity = null;
//        if(dto == null) return entity;
//        if(dto.getId() == null) {
//            //Создаем нового пользователя
//            entity = new User();
//            entity.setUsername(dto.getUsername());
//            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
//            entity.getUserMembership().setCreateDate(LocalDateTime.now());
//            entity.getUserMembership().setLastPasswordChangeDate(LocalDateTime.now());
//            entity.getUserMembership().setLastLoginDate(LocalDateTime.now());
////            if(!dto.getRoles().isEmpty()){
////                dto.getRoles().forEach(role -> addUserInRole(dto.getUsername(), role.getName()));
////            }
//        } else {
//            entity = userRepository.findOneByUsername(dto.getUsername().toLowerCase());
//            if (entity == null) {
//                return entity;
//            }
//        }
//        entity.setPasswordQuestion(dto.getPasswordQuestion());
//        entity.setPasswordAnswer(dto.getPasswordAnswer());
//        entity.getUserMembership().setApproved(dto.isApproved());
//        entity.getUserMembership().setComment(dto.getComment());
//        return userRepository.save(entity);
//    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean changePassword(String userName, String oldPsw, String newPsw) {
        boolean res = true;
        User user = userRepository.findOneByUsername(userName);
        if(user == null || !passwordEncoder.matches(oldPsw, user.getPassword())) return false;
        user.setPassword(passwordEncoder.encode(newPsw));
        user.getUserMembership().setLastPasswordChangeDate(LocalDateTime.now());
        userRepository.save(user);
        return res;
    }

    public boolean changePasswordByPasswordAnswer(String userName, String newPsw, String passwordAnswer) {
        boolean res = true;
        User user = userRepository.findOneByUsername(userName);
        if(user == null || !passwordEncoder.matches(user.getPasswordAnswer(), passwordAnswer)) return false;
        user.setPassword(passwordEncoder.encode(newPsw));
        user.getUserMembership().setLastPasswordChangeDate(LocalDateTime.now());
        userRepository.save(user);
        return res;
    }

    public void registerUser(String username, String password, String email, Collection<UserRoleEnum> rolesEnum) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreationDate(LocalDateTime.now());
        user.setLastUpdate(LocalDateTime.now());
        List<Role> roles = new ArrayList<>();
        for (UserRoleEnum roleEnum : rolesEnum) {
            Role role = roleRepository.findOneByName(roleEnum.getName());
            roles.add(role);
        }
        user.setRoles(roles);
        userRepository.save(user);
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
    public User save(UserDto userDto) {
        if (userEmailExists(userDto.getUsername())) {
            throw new UserAlreadyExistException("Пользователь с таким email email уже существует: " + userDto.getEmail());
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail().toLowerCase());
        user.getUserMembership().setCreateDate(LocalDateTime.now());
        user.getUserMembership().setLastPasswordChangeDate(LocalDateTime.now());
        user.getUserMembership().setLastLoginDate(LocalDateTime.now());
        user.setRoles(Arrays.asList(roleRepository.findOneByName("ROLE_USER")));
        user.setPasswordQuestion(userDto.getPasswordQuestion());
        user.setPasswordAnswer(userDto.getPasswordAnswer());
        user.getUserMembership().setApproved(userDto.isApproved());
        user.getUserMembership().setComment(userDto.getComment());
        return user;
    }

    private boolean userEmailExists(String email) {
        User user = userRepository.findOneByEmail(email);
        if(user != null) {
            return true;
        }
        return false;
    }

}
