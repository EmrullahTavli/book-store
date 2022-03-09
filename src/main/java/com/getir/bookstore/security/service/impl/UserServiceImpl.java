package com.getir.bookstore.security.service.impl;

import com.getir.bookstore.entity.User;
import com.getir.bookstore.security.repository.UserRepository;
import com.getir.bookstore.security.service.UserService;
import com.getir.bookstore.web.exception.BookStoreException;
import com.getir.bookstore.security.web.mapper.UserMapper;
import com.getir.bookstore.security.web.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new BookStoreException("User not found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    @Override
    public UserDto save(UserDto userDto) {
        if (userRepository.existsByName(userDto.getName())) {
            throw new BookStoreException("Username is already taken!");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new BookStoreException("Email is already in use!");
        }

        User user = userMapper.map(userDto);
        return userMapper.map(userRepository.save(user));
    }
}
