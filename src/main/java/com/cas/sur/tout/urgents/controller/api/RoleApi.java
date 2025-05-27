package com.cas.sur.tout.urgents.controller.api;

import com.cas.sur.tout.urgents.dto.RoleDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RoleApi {

    @GetMapping("/all")
    List<RoleDto> findAll();

    @PostMapping("/add")
    RoleDto save(@RequestBody RoleDto dto);

    @PutMapping("/update")
    RoleDto update(@RequestBody RoleDto dto);

    @GetMapping("/find/{role}")
    RoleDto findByRole(@PathVariable String role);

    @PutMapping("/isActive/{id}")
    void disableRole(@PathVariable("id") Long id);

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id);
}
