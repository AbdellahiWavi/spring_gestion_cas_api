package com.cas.sur.tout.urgents.service.impl;

import com.cas.sur.tout.urgents.dto.RoleDto;
import com.cas.sur.tout.urgents.exception.EntityNotFoundException;
import com.cas.sur.tout.urgents.exception.ErrorCodes;
import com.cas.sur.tout.urgents.exception.InvalidEntityException;
import com.cas.sur.tout.urgents.model.Role;
import com.cas.sur.tout.urgents.repository.RoleRepo;
import com.cas.sur.tout.urgents.service.RoleService;
import com.cas.sur.tout.urgents.validator.RoleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public RoleDto save(RoleDto dto) {
        List<String> errors = RoleValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("l'objet est vide {}", dto);
            throw new InvalidEntityException(
                    "Veuillez renseigner toutes les champs",
                    ErrorCodes.ROLE_NOT_VALID,
                    errors
            );
        }

        dto.setActive(true);
        return RoleDto.fromEntity(
                roleRepo.save(
                        RoleDto.toEntity(dto)
                ));
    }

    @Override
    public RoleDto findByRole(String role) {
        if (!StringUtils.hasLength(role)) {
            log.error("Le rôle est vide ou null");
            throw new InvalidEntityException("Veuillez renseigner toutes les champs");
        }
        return roleRepo.findByRole(role)
                .map(RoleDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun rôle '" + role + "' n'a été trouvé dans la BDD",
                        ErrorCodes.ROLE_NOT_FOUND
                ));
    }

    @Override
    public void disableRoleById(Long id) {
        if (id == null) {
            log.error("l'id du degree est nulle");
            return;
        }

        Role role = roleRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Aucune role avec l'ID = " + id,
                        ErrorCodes.ROLE_NOT_FOUND)
        );

        role.setActive(false);
        roleRepo.save(role);
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepo.findAll().stream()
                .map(RoleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("l'id qui passe en parametre est nulle");
            return;
        }
        roleRepo.deleteById(id);
    }

}
