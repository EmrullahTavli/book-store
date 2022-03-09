package com.getir.bookstore.security.web.mapper;

import com.getir.bookstore.entity.User;
import com.getir.bookstore.security.web.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto map(User user);

    User map(UserDto userDto);
}
