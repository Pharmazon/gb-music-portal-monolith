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
import ru.geekbrains.musicportal.repository.RoleRepository;
import ru.geekbrains.musicportal.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
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
        return userRepository.findByUserName(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName.toLowerCase());
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

    @Override
    @Transactional
    public User save(UserDto dto) {
        User entity = null;
        if(dto == null) return entity;
        if(dto.getId() == null) {
            //Создаем нового пользователя
            entity = new User();
            entity.setUsername(dto.getUsername());
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
            entity.getUserMembership().setCreateDate(LocalDateTime.now());
            entity.getUserMembership().setLastPasswordChangeDate(LocalDateTime.now());
            entity.getUserMembership().setLastLoginDate(LocalDateTime.now());
            if(!dto.getRoles().isEmpty()){
                dto.getRoles().forEach(role -> addUserInRole(dto.getUsername(), role.getName()));
            }
        } else {
            entity = userRepository.findByUserName(dto.getUsername().toLowerCase());
            if (entity == null) {
                return entity;
            }
        }
        entity.setPasswordQuestion(dto.getPasswordQuestion());
        entity.setPasswordAnswer(dto.getPasswordAnswer());
        entity.getUserMembership().setApproved(dto.isApproved());
        entity.getUserMembership().setComment(dto.getComment());
        return userRepository.save(entity);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean changePassword(String userName, String oldPsw, String newPsw) {
        boolean res = true;
        User user = userRepository.findByUserName(userName);
        if(user == null || !passwordEncoder.matches(oldPsw, user.getPassword())) return false;
        user.setPassword(passwordEncoder.encode(newPsw));
        user.getUserMembership().setLastPasswordChangeDate(LocalDateTime.now());
        userRepository.save(user);
        return res;
    }

    public boolean changePasswordByPasswordAnswer(String userName, String newPsw, String passwordAnswer) {
        boolean res = true;
        User user = userRepository.findByUserName(userName);
        if(user == null || !passwordEncoder.matches(user.getPasswordAnswer(), passwordAnswer)) return false;
        user.setPassword(passwordEncoder.encode(newPsw));
        user.getUserMembership().setLastPasswordChangeDate(LocalDateTime.now());
        userRepository.save(user);
        return res;
    }

    public boolean addUserInRole(String userName, String roleName){
        boolean res = false;
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findOneByName(roleName);
        if(user != null && role != null) {
            role.getUsers().add(user);
            roleRepository.save(role);
            res = true;
        }
        return res;
    }

    public boolean removeUserFromRole(String userName, String roleName){
        boolean res = false;
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findOneByName(roleName);
        if(user != null && role != null){
            User remove = role.getUsers().stream().filter(u -> Objects.equals(u.getId(), user.getId())).findFirst().get();
            role.getUsers().remove(remove);
            roleRepository.save(role);
            res = true;
        }
        return res;
    }
}
