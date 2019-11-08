package com.example.pm.dto;

import com.example.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class UserDtoConverter implements Converter<Long, UserDto> {

    @Autowired
    private UserService userService;


    @Override
    public UserDto convert(Long id) {
        return userService.finById(id);
    }
}
