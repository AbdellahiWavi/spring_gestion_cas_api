package com.cas.sur.tout.urgents.service.impl;

import com.cas.sur.tout.urgents.config.EncoderConfig;
import com.cas.sur.tout.urgents.dto.ClientDto;
import com.cas.sur.tout.urgents.dto.GestionnaireDto;
import com.cas.sur.tout.urgents.dto.RoleDto;
import com.cas.sur.tout.urgents.exception.EntityNotFoundException;
import com.cas.sur.tout.urgents.exception.ErrorCodes;
import com.cas.sur.tout.urgents.exception.InvalidEntityException;
import com.cas.sur.tout.urgents.model.Client;
import com.cas.sur.tout.urgents.model.Gestionnaire;
import com.cas.sur.tout.urgents.model.Incident;
import com.cas.sur.tout.urgents.model.Role;
import com.cas.sur.tout.urgents.repository.ClientRepo;
import com.cas.sur.tout.urgents.repository.RoleRepo;
import com.cas.sur.tout.urgents.service.ClientService;
import com.cas.sur.tout.urgents.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;
    private final RoleRepo roleRepo;

    @Autowired
    private EncoderConfig encoder;

    @Autowired
    public ClientServiceImpl(ClientRepo clientRepo, RoleRepo roleRepo) {
        this.clientRepo = clientRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors = ClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Client n'est pas valide {}", dto);
            throw new InvalidEntityException(
                    "Client n'est pas valide",
                    ErrorCodes.CLIENT_NOT_VALID, errors
            );
        }
        dto.setActive(true);

        Optional<RoleDto> roleprofileDto = Optional.ofNullable(RoleDto.fromEntity(roleRepo.findByRoleProfile("USER", "RIEN")));

        if (dto.getRoles() == null || dto.getRoles().isEmpty()) {
            dto.setRoles(roleprofileDto.map(List::of)
                    .orElseGet(() -> List.of(RoleDto.fromEntity(
                                    roleRepo.save(Role.builder().role("USER").profile("RIEN").build())
                            ))
                    )
            );
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            dto.setPassword(encoder.passwordEncoder().encode(dto.getPassword()));
        } else {
            log.warn("Mot de passe vide ou null pour le gestionnaire : {}", dto.getUsername());
        }

        return ClientDto.fromEntity(
                clientRepo.save(
                        ClientDto.toEntity(dto)
                )
        );
    }

    @Override
    public ClientDto update(ClientDto dto) {
        Client existingUser = clientRepo.findById(dto.getId()).orElseThrow();

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            if (!encoder.passwordEncoder().matches(dto.getPassword(), existingUser.getPassword())) {
                existingUser.setPassword(encoder.passwordEncoder().encode(dto.getPassword()));
            }
        }
        return ClientDto.fromEntity(
                clientRepo.save(
                        ClientDto.toEntity(dto)
                ));
    }

    @Override
    public ClientDto findById(Long id) {
        if (id == null) {
            log.error("L'ID du client est nulle");
            return null;
        }
        return clientRepo.findById(id)
                .map(ClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Le client avec l'id = " + id + " n'existe pas",
                        ErrorCodes.CLIENT_NOT_FOUND
                ));
    }

    @Override
    public ClientDto findClientByTel(String tel) {
        if (tel == null) {
            log.error("Le numero de telephone du client est nulle");
            return null;
        }
        return clientRepo.findClientByTel(tel)
                .map(ClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun client avec ce numero de telephone '" + tel + "' n'existe pas",
                        ErrorCodes.CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepo.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("L'id du client est nulle");
            return;
        }
        clientRepo.deleteById(id);
    }

    @Override
    public void disableClientById(Long id) {
        if (id == null) {
            log.error("L'ID du client à désactiver est nulle");
            return;
        }

        Client client = clientRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Aucune client avec l'ID = " + id,
                        ErrorCodes.CLIENT_NOT_FOUND)
        );

        client.setActive(false);
        clientRepo.save(client);
    }
}