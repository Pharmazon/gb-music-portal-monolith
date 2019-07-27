package ru.geekbrains.musicportal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.config.TokenProvider;
import ru.geekbrains.musicportal.dto.user.JwtResponse;
import ru.geekbrains.musicportal.dto.user.UserAuthDto;
import ru.geekbrains.musicportal.marker.UserAuthViews;
import ru.geekbrains.musicportal.response.UserResponse;
import ru.geekbrains.musicportal.response.common.ResponseWrapper;

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

    @JsonView(UserAuthViews.All.class)
    @PostMapping
    public ResponseWrapper authUser(@RequestBody UserAuthDto userAuthDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthDto.getUsername(), userAuthDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return ResponseWrapper.ok(jwtResponse, UserResponse.SUCCESS_AUTHENTICATED);
    }
}
