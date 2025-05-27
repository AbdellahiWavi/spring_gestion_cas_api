package com.cas.sur.tout.urgents.service;

import com.cas.sur.tout.urgents.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto dto);

    UserDto findById(Long id);

    List<UserDto> findAll();

    void deleteById(Long id);

}
