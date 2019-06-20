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
import ru.geekbrains.musicportal.service.security.UserService;

import java.time.LocalDateTime;
import java.util.Collection;
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
            throw new UsernameNotFoundException("Invalid username or password.");
        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                mapRolesToAuthorities(user.getRoles()));
        return user;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String encodePassword(String passwordRaw, String passwordSalt){
        StringBuilder saltAndPwd = new StringBuilder(passwordRaw + passwordSalt);
        String encodePwd = null;

        for(int i = 0; i < 1; i++){
            encodePwd = passwordEncoder.encode(saltAndPwd.toString());
            saltAndPwd.append(encodePwd);
        }

        return encodePwd;
    }

    public boolean save(User user) {
        return true;
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
}
