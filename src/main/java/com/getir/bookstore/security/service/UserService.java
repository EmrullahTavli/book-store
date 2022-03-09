package com.getir.bookstore.security.service;

import com.getir.bookstore.security.web.model.dto.UserDto;

public interface UserService {
    UserDto save(UserDto user);
}
