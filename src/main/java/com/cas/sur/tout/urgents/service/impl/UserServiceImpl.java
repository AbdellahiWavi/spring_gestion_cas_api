package com.cas.sur.tout.urgents.service.impl;

import com.cas.sur.tout.urgents.dto.GestionnaireDto;
import com.cas.sur.tout.urgents.dto.UserDto;
import com.cas.sur.tout.urgents.exception.EntityNotFoundException;
import com.cas.sur.tout.urgents.exception.ErrorCodes;
import com.cas.sur.tout.urgents.exception.InvalidEntityException;
import com.cas.sur.tout.urgents.repository.UserRepo;
import com.cas.sur.tout.urgents.service.UserService;
import com.cas.sur.tout.urgents.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto saveUser(UserDto dto) {
        List<String> errors = UserValidator.validate((GestionnaireDto) dto);
        if (!errors.isEmpty()) {
            log.error("l'utilisateur est invalide {}", dto);
            throw new InvalidEntityException(
                    "l'utilisateur est invalide",
                    ErrorCodes.USER_NOT_VALID,
                    errors
            );
        }

        return UserDto.fromEntity(
                userRepo.save(
                        UserDto.toEntity(dto)
                ));
    }

    @Override
    public UserDto findById(Long id) {
        if (id == null) {
            log.error("l'id de l'utilisateur est nulle");
            return null;
        }
        return userRepo.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "l'utilisateur est introuvée",
                        ErrorCodes.USER_NOT_FOUND
                ));
    }

    @Override
    public List<UserDto> findAll() {

        return userRepo.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("Il y a pas un utilisateur avec id nulle");
            return;
        }
        userRepo.deleteById(id);
    }

}
