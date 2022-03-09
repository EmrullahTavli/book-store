package com.getir.bookstore.security.web.model.response;

import com.getir.bookstore.security.web.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String jwt;
    private UserDto user;
}
