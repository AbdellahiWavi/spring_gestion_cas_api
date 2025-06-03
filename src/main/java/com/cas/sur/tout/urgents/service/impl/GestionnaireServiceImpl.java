package com.cas.sur.tout.urgents.service.impl;

import com.cas.sur.tout.urgents.config.EncoderConfig;
import com.cas.sur.tout.urgents.dto.GestionnaireDto;
import com.cas.sur.tout.urgents.dto.RoleDto;
import com.cas.sur.tout.urgents.exception.EntityNotFoundException;
import com.cas.sur.tout.urgents.exception.ErrorCodes;
import com.cas.sur.tout.urgents.exception.InvalidEntityException;
import com.cas.sur.tout.urgents.model.Gestionnaire;
import com.cas.sur.tout.urgents.model.Role;
import com.cas.sur.tout.urgents.repository.GestionnaireRepo;
import com.cas.sur.tout.urgents.repository.RoleRepo;
import com.cas.sur.tout.urgents.service.GestionnaireService;
import com.cas.sur.tout.urgents.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class GestionnaireServiceImpl implements GestionnaireService {

    private final GestionnaireRepo gestionnaireRepo;
    private final RoleRepo roleRepo;

    @Autowired
    private EncoderConfig encoder;

    @Autowired
    public GestionnaireServiceImpl(GestionnaireRepo gestionnaireRepo, RoleRepo roleRepo) {
        this.gestionnaireRepo = gestionnaireRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public GestionnaireDto save(GestionnaireDto dto) {
        List<String> errors = UserValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Il manque des informations concernant le gestionnaire {}", dto);
            throw new InvalidEntityException(
                    "Il manque des informations concernant le gestionnaire",
                    ErrorCodes.GESTIONNAIRE_NOT_VALID,
                    errors
            );
        }
        dto.setActive(true);

        if (dto.getRoles() == null || dto.getRoles().isEmpty()) {
            Optional<RoleDto> roleprofileDto = Optional.ofNullable(RoleDto.fromEntity(roleRepo.findByRoleProfile("ADMIN", "TOUTES")));

            dto.setRoles(roleprofileDto.map(List::of)
                    .orElseGet(() -> List.of(RoleDto.fromEntity(
                                    roleRepo.save(Role.builder().role("ADMIN").profile("TOUTES").build())
                            ))
                    )
            );
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            dto.setPassword(encoder.passwordEncoder().encode(dto.getPassword()));
        } else {
            log.warn("Mot de passe vide ou null pour le gestionnaire : {}", dto.getEmail());
        }

        return GestionnaireDto.fromEntity(
                gestionnaireRepo.save(
                        GestionnaireDto.toEntity(dto)
                ));
    }

    @Override
    public GestionnaireDto update(GestionnaireDto dto) {
        Gestionnaire existingUser = gestionnaireRepo.findById(dto.getId()).orElseThrow();

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            if (!encoder.passwordEncoder().matches(dto.getPassword(), existingUser.getPassword())) {
                existingUser.setPassword(encoder.passwordEncoder().encode(dto.getPassword()));
            }
        }
        return GestionnaireDto.fromEntity(
                gestionnaireRepo.save(
                        GestionnaireDto.toEntity(dto)
                ));
    }

    @Override
    public GestionnaireDto findById(Long id) {
        if (id == null) {
            log.error("l'id de la gestionnaite est nulle");
            return null;
        }

        return gestionnaireRepo.findById(id)
                .map(GestionnaireDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune gestionnaire avec l'id " + id,
                        ErrorCodes.GESTIONNAIRE_NOT_FOUND
                ));
    }

    @Override
    public GestionnaireDto findUserByEmail(String email) {
        if (email == null) {
            log.error("l'email de la gestionnaite est nulle");
            return null;
        }

        return gestionnaireRepo.findUserByEmail(email)
                .map(GestionnaireDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune gestionnaire avec l'email " + email,
                        ErrorCodes.GESTIONNAIRE_NOT_FOUND
                ));
    }

    @Override
    public List<GestionnaireDto> findAll() {
        return gestionnaireRepo.findAll().stream()
                .map(GestionnaireDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("l'id de la gestionnaire est nulle");
            return;
        }
        gestionnaireRepo.deleteById(id);
    }

    @Override
    public void disableUser(Long id) {
        Gestionnaire gestionnaire = gestionnaireRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Aucun gestionnaire avec l'ID = " + id,
                        ErrorCodes.GESTIONNAIRE_NOT_FOUND)
        );

        gestionnaire.setActive(false);
        gestionnaireRepo.save(gestionnaire);
    }
}
