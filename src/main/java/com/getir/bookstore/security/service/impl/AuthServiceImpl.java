package com.getir.bookstore.security.service.impl;

import com.getir.bookstore.security.web.model.enumeration.Role;
import com.getir.bookstore.security.web.model.dto.UserDto;
import com.getir.bookstore.security.service.AuthService;
import com.getir.bookstore.security.service.UserService;
import com.getir.bookstore.util.JwtUtils;
import com.getir.bookstore.security.web.model.response.JwtResponse;
import com.getir.bookstore.security.web.model.request.LoginRequest;
import com.getir.bookstore.security.web.model.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;


    @Override
    public ResponseEntity<JwtResponse> signIn(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Role> role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(Role::valueOf)
                .findAny();

        return ResponseEntity.ok(JwtResponse.builder()
                .jwt(jwt)
                .user(UserDto.builder()
                        .id(userDetails.getId())
                        .name(userDetails.getUsername())
                        .email(userDetails.getEmail())
                        .role(role.orElse(null))
                        .build())
                .build());
    }

    @Override
    public ResponseEntity<String> signUp(SignupRequest request) {

        UserDto user = UserDto.builder()
                .name(request.getUserName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userService.save(user);
        return ResponseEntity.ok("User registered successfully!");

    }
}
