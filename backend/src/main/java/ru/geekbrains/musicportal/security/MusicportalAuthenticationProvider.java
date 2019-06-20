package ru.geekbrains.musicportal.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import ru.geekbrains.musicportal.service.security.UserService;
import ru.geekbrains.musicportal.entity.security.User;

import java.time.LocalDateTime;
import java.util.Date;

public final class MusicportalAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory
            .getLogger(MusicportalAuthenticationProvider.class);

    private UserService userService;
    private BCryptPasswordEncoder bcryptEncoder;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String password = authentication.getCredentials().toString();
        String userName = authentication.getName();

        User user = (User)userService.loadUserByUsername(userName);

        if (user == null) {
            logger.error("User not found. UserName=" + userName);
            throw new BadCredentialsException(userName);
        } else if (!user.isAccountNonLocked()) {
            logger.error("Not activated.");
            throw new LockedException(userName);
        } else if (!user.isEnabled()) {
            logger.error("Not enabled.");
            throw new DisabledException(userName);
        } else {
            if (bcryptEncoder.matches(password, user.getPassword())) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());

                user.getUserMembership().setLastLoginDate(LocalDateTime.now());
                user.getUserMembership().setFailedPasswordAnswerAttemptCount(0);
                user.getUserMembership().setFailedPasswordAnswerAttemptWindowStart(null);
                userService.save(user);

                logger.info("authenticate " + userName);
                return token;
            } else {
                user.getUserMembership().setFailedPasswordAnswerAttemptCount(
                        user.getUserMembership().getFailedPasswordAnswerAttemptCount() + 1);
                user.getUserMembership().setFailedPasswordAnswerAttemptWindowStart(LocalDateTime.now());
                userService.save(user);

                logger.error("Password does not match. UserName=" + userName);
                throw new BadCredentialsException(userName);
            }
        }
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication);
    }
}
