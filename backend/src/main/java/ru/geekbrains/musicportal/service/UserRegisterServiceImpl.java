package ru.geekbrains.musicportal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.musicportal.dto.UserRegistrationDto;
import ru.geekbrains.musicportal.entity.TestRole;
import ru.geekbrains.musicportal.entity.TestUser;
import ru.geekbrains.musicportal.exception.UserAlreadyExistException;
import ru.geekbrains.musicportal.repository.TestRoleRepository;
import ru.geekbrains.musicportal.repository.TestUserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 *Юзер сервис в котором происходит регистрация нового пользователя, можно оставить отдельным сервисом или слить
 * в единый юзер сервис.
 * Существующий юзер проверяется по email если существует выбрасывается исключение.
 */
@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    private TestUserRepository testUserRepository;
    private TestRoleRepository testRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public void setTestUserRepository(TestUserRepository testUserRepository) {
        this.testUserRepository = testUserRepository;
    }

    @Autowired
    public void setTestRoleRepository(TestRoleRepository testRoleRepository) {
        this.testRoleRepository = testRoleRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public TestUser findByUserName(String username) {
        return testUserRepository.fondByName(username);
    }

    @Override
    @Transactional
    public TestUser findByEmail(String email) {
        return testUserRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public boolean save(UserRegistrationDto userRegistrationDto) {
        if (emailExists(userRegistrationDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userRegistrationDto.getEmail());
        }
        TestUser testUser = new TestUser();
        testUser.setFirstName(userRegistrationDto.getFirstName());
        testUser.setLastName(userRegistrationDto.getLastName());
        testUser.setPassword(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()));
        testUser.setEmail(userRegistrationDto.getEmail());
        testUser.setRoles(Arrays.asList(testRoleRepository.findOneByName("ROLE_ADMIN")));
        testUserRepository.save(testUser);
        return true;
    }

    private boolean emailExists(String email) {
        TestUser testUser = testUserRepository.findByEmail(email);
        if(testUser != null) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
         TestUser testUser = testUserRepository.fondByName(userName);
        if (testUser == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(testUser.getUserName(), testUser.getPassword(),
                mapRolesToAuthorities(testUser.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<TestRole> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
