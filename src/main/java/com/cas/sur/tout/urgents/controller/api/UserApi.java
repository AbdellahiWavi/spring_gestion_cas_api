package com.cas.sur.tout.urgents.controller.api;

import com.cas.sur.tout.urgents.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserApi {

    @GetMapping("/all")
    List<UserDto> findAll();

    @PostMapping("/add")
    UserDto save(@RequestBody UserDto dto);

    @PutMapping("/update")
    UserDto update(@RequestBody UserDto dto);

    @GetMapping("/find/{id}")
    UserDto findById(@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id);

}
