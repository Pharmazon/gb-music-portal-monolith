package ru.geekbrains.musicportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.config.TokenProvider;
import ru.geekbrains.musicportal.dto.user.JwtResponse;
import ru.geekbrains.musicportal.dto.user.UserAuthDto;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/authorization")
public class AuthRestController {

    private AuthenticationManager authenticationManager;
    private TokenProvider jwtProvider;

    @Autowired
    public AuthRestController(AuthenticationManager authenticationManager,
                              TokenProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping
    public ResponseEntity<?> authUser(@RequestBody UserAuthDto userAuthDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthDto.getUsername(), userAuthDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }
}
