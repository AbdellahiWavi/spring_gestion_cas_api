package com.cas.sur.tout.urgents.controller;

import com.cas.sur.tout.urgents.controller.api.RoleApi;
import com.cas.sur.tout.urgents.dto.RoleDto;
import com.cas.sur.tout.urgents.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cas.sur.tout.urgents.utils.Constants.ROLE_ENDPOINT;

@RestController
@RequestMapping(ROLE_ENDPOINT)
public class RoleController implements RoleApi {

    private final RoleServiceImpl roleService;

    @Autowired
    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Override
    public List<RoleDto> findAll() {
        return roleService.findAll();
    }

    @Override
    public RoleDto save(RoleDto dto) {
        return roleService.save(dto);
    }

    @Override
    public RoleDto update(RoleDto dto) {
        return roleService.save(dto);
    }

    @Override
    public RoleDto findByRole(String role) {
        return roleService.findByRole(role);
    }

    @Override
    public void disableRole(Long id) {
        roleService.disableRoleById(id);
    }

    @Override
    public void deleteById(Long id) {
        roleService.deleteById(id);

    }
}
