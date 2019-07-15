//package ru.geekbrains.musicportal.security;
//
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import ru.geekbrains.musicportal.entity.user.User;
//import ru.geekbrains.musicportal.service.user.UserService;
//
//import java.time.LocalDateTime;
//
//@Slf4j
//@Setter
//@NoArgsConstructor
//public final class MusicportalAuthenticationProvider implements AuthenticationProvider {
//
//    private UserService userService;
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public MusicportalAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication)
//            throws AuthenticationException {
//
//        String password = authentication.getCredentials().toString();
//        String userName = authentication.getName();
//
//        User user = (User)userService.loadUserByUsername(userName);
//
//        if (user == null) {
//            log.error("User not found. UserName=" + userName);
//            throw new BadCredentialsException(userName);
//        } else if (!user.isAccountNonLocked()) {
//            log.error("Not activated.");
//            throw new LockedException(userName);
//        } else if (!user.isEnabled()) {
//            log.error("Not enabled.");
//            throw new DisabledException(userName);
//        } else {
//            if (passwordEncoder.matches(password, user.getPassword())) {
//                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                        user, null, user.getAuthorities());
//
//                user.setLastLoginDate(LocalDateTime.now());
//                user.setFailedPasswordAnswerAttemptCount(0);
//                user.setFailedPasswordAnswerAttemptWindowStart(null);
//                userService.save(user);
//
//                log.info("authenticate " + userName);
//                return token;
//            } else {
//                user.setFailedPasswordAnswerAttemptCount(
//                        user.getFailedPasswordAnswerAttemptCount() + 1);
//                user.setFailedPasswordAnswerAttemptWindowStart(LocalDateTime.now());
//                userService.save(user);
//
//                log.error("Password does not match. UserName=" + userName);
//                throw new BadCredentialsException(userName);
//            }
//        }
//    }
//
//    @Override
//    public boolean supports(Class<? extends Object> authentication) {
//        return UsernamePasswordAuthenticationToken.class
//                .isAssignableFrom(authentication);
//    }
//}
