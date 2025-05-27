package com.cas.sur.tout.urgents.controller;

import com.cas.sur.tout.urgents.controller.api.UserApi;
import com.cas.sur.tout.urgents.dto.IncidentDto;
import com.cas.sur.tout.urgents.dto.UserDto;
import com.cas.sur.tout.urgents.model.Status;
import com.cas.sur.tout.urgents.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cas.sur.tout.urgents.utils.Constants.USER_ENDPOINT;

@RestController
@RequestMapping(USER_ENDPOINT)
public class UserController implements UserApi {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @Override
    public UserDto save(UserDto dto) {
        return userService.saveUser(dto);
    }

    @Override
    public UserDto update(UserDto dto) {
        return userService.saveUser(dto);
    }

    @Override
    public UserDto findById(Long id) {
        return userService.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userService.deleteById(id);
    }

}
