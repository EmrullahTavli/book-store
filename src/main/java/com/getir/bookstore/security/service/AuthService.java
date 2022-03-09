package com.getir.bookstore.security.service;

import com.getir.bookstore.security.web.model.response.JwtResponse;
import com.getir.bookstore.security.web.model.request.LoginRequest;
import com.getir.bookstore.security.web.model.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<JwtResponse> signIn(LoginRequest request);

    ResponseEntity<String> signUp(SignupRequest request);
}
