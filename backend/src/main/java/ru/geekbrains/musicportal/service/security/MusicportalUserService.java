package ru.geekbrains.musicportal.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.geekbrains.musicportal.entity.security.User;
import ru.geekbrains.musicportal.entity.security.Role;
import ru.geekbrains.musicportal.repository.RoleRepository;
import ru.geekbrains.musicportal.repository.UserRepository;
import ru.geekbrains.musicportal.dto.UserDto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MusicportalUserService implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public User save(UserDto source) {
        User res = null;

        if(source == null)
            return res;

        if(source.getId() == null){
            //Создаем нового пользователя
            res = new User();
            res.setUserID(UUID.randomUUID());
            res.setUserName(source.getUserName());
            res.setLoweredUserName(source.getUserName().toLowerCase());
            res.setPassword(passwordEncoder.encode(source.getPassword()));

            res.getUserMembership().setCreateDate(LocalDateTime.now());
            res.getUserMembership().setLastPasswordChangeDate(LocalDateTime.now());
            res.getUserMembership().setLastLoginDate(LocalDateTime.now());

            if(!source.getRoles().isEmpty()){
                source.getRoles().stream().forEach(role -> addUserInRole(source.getUserName(), role));
            }

        }else{
            res = userRepository.findByUserName(source.getUserName().toLowerCase());
            if (res == null) {
                return res;
            }
        }

        res.setPasswordQuestion(source.getPasswordQuestion());
        res.setPasswordAnswer(source.getPasswordAnswer());
        res.getUserMembership().setApproved(source.isApproved());
        res.getUserMembership().setComment(source.getComment());

        return userRepository.save(res);
    }

    public boolean changePassword(String userName, String oldPsw, String newPsw) {
        boolean res = true;

        User user = userRepository.findByUserName(userName);

        if(user == null || !passwordEncoder.matches(oldPsw, user.getPassword()))
            return false;

        user.setPassword(passwordEncoder.encode(newPsw));
        user.getUserMembership().setLastPasswordChangeDate(LocalDateTime.now());
        userRepository.save(user);

        return res;
    }

    public boolean changePasswordByPasswordAnswer(String userName, String newPsw, String passwordAnswer) {
        boolean res = true;

        User user = userRepository.findByUserName(userName);

        if(user == null || !passwordEncoder.matches(user.getPasswordAnswer(), passwordAnswer))
            return false;

        user.setPassword(passwordEncoder.encode(newPsw));
        user.getUserMembership().setLastPasswordChangeDate(LocalDateTime.now());
        userRepository.save(user);

        return res;
    }

    public boolean addUserInRole(String userName, String roleName){
        boolean res = false;

        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findOneByName(roleName);
        if(user != null && role != null){
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
            User remove = role.getUsers().stream().filter(u -> Objects.equals(u.getUserID(), user.getUserID())).findFirst().get();
            role.getUsers().remove(remove);
            roleRepository.save(role);
            res = true;
        }

        return res;
    }
}
