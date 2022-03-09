package com.getir.bookstore.security.web.model.dto;

import com.getir.bookstore.security.web.model.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String email;
    private Role role;
}
