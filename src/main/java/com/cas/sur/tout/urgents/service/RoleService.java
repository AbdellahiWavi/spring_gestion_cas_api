package com.cas.sur.tout.urgents.service;

import com.cas.sur.tout.urgents.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto save(RoleDto dto);

    RoleDto findByRole(String role);

    void disableRoleById(Long id);

    List<RoleDto> findAll();

    void deleteById(Long id);

}
