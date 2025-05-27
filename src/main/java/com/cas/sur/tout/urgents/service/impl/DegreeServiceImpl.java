package com.cas.sur.tout.urgents.service.impl;

import com.cas.sur.tout.urgents.dto.ClientDto;
import com.cas.sur.tout.urgents.dto.DegreeDto;
import com.cas.sur.tout.urgents.exception.EntityNotFoundException;
import com.cas.sur.tout.urgents.exception.ErrorCodes;
import com.cas.sur.tout.urgents.exception.InvalidEntityException;
import com.cas.sur.tout.urgents.model.Client;
import com.cas.sur.tout.urgents.model.Degree;
import com.cas.sur.tout.urgents.repository.DegreeRepo;
import com.cas.sur.tout.urgents.service.DegreeService;
import com.cas.sur.tout.urgents.validator.DegreeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class DegreeServiceImpl implements DegreeService {

    private final DegreeRepo degreeRepo;

    @Autowired
    public DegreeServiceImpl(DegreeRepo degreeRepo) {
        this.degreeRepo = degreeRepo;
    }

    @Override
    public List<DegreeDto> findAll() {
        return degreeRepo.findAll().stream()
                .map(DegreeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DegreeDto save(DegreeDto dto) {
        List<String> errors = DegreeValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("le champ de type de degree est vide {}", dto);
            throw new InvalidEntityException(
                    "le type de degree est invalide",
                    ErrorCodes.DEGREE_NOT_VALID,
                    errors
            );
        }

        dto.setActive(true);
        return DegreeDto.fromEntity(
                degreeRepo.save(
                        DegreeDto.toEntity(dto)
                )
        );
    }

    @Override
    public DegreeDto findById(Long id) {
        if (id == null) {
            log.error("L'id de degree est nulle");
            return null;
        }
        return degreeRepo.findById(id)
                .map(DegreeDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "C'est type de gree est introuvable",
                        ErrorCodes.DEGREE_NOT_FOUND
                ));
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("l'id du degree est nulle");
            return;
        }

        degreeRepo.deleteById(id);
    }

    @Override
    public void disableDegreeById(Long id) {
        Degree degree = degreeRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Aucun degree avec l'ID = " + id,
                        ErrorCodes.DEGREE_NOT_FOUND)
        );

        degree.setActive(false);
        degreeRepo.save(degree);
    }
}
