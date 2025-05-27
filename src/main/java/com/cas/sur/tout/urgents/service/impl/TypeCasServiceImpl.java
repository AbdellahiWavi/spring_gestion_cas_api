package com.cas.sur.tout.urgents.service.impl;

import com.cas.sur.tout.urgents.dto.OrganismeExtDto;
import com.cas.sur.tout.urgents.dto.TypeCasDto;
import com.cas.sur.tout.urgents.exception.EntityNotFoundException;
import com.cas.sur.tout.urgents.exception.ErrorCodes;
import com.cas.sur.tout.urgents.exception.InvalidEntityException;
import com.cas.sur.tout.urgents.model.OrganismeExt;
import com.cas.sur.tout.urgents.model.TypeCas;
import com.cas.sur.tout.urgents.repository.OrganismeExtRepo;
import com.cas.sur.tout.urgents.repository.TypeCasRepo;
import com.cas.sur.tout.urgents.service.TypeCasService;
import com.cas.sur.tout.urgents.validator.TypeCasValidator;
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
public class TypeCasServiceImpl implements TypeCasService {

    private final TypeCasRepo typeCasRepo;
    private final OrganismeExtRepo organismeExtRepo;

    @Autowired
    public TypeCasServiceImpl(TypeCasRepo typeCasRepo, OrganismeExtRepo organismeExtRepo) {
        this.typeCasRepo = typeCasRepo;
        this.organismeExtRepo = organismeExtRepo;
    }

    @Override
    public TypeCasDto save(TypeCasDto dto) {
        List<String> errors = TypeCasValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("le type de cas est invalide {}", dto);
            throw new InvalidEntityException(
                    "le type de cas est invalide",
                    ErrorCodes.TYPE_CAS_NOT_FOUND, errors
            );
        }

        Optional<OrganismeExt> organismeExt = organismeExtRepo.findById(dto.getDestination().getIdDestination());
        if (organismeExt.isEmpty()) {
            log.warn("L'organisme exterieur avec l'id {} n'a ete trouver dans la BDD", dto.getDestination().getIdDestination());
            throw new EntityNotFoundException(
                    "Aucune organisme exterieur avec l'id " + dto.getDestination().getIdDestination() + " n'a ete trouver dans la BDD",
                    ErrorCodes.ORGANISME_NOT_FOUND
            );
        }

        dto.setActive(true);
        return TypeCasDto.fromEntity(
                typeCasRepo.save(
                        TypeCasDto.toEntity(dto)
                ));
    }

    @Override
    public TypeCasDto findById(Integer id) {
        if (id == null) {
            log.error("l'id de la type de cas est nulle");
            return null;
        }
        return typeCasRepo.findById(id)
                .map(TypeCasDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Le type de cas est introuvable",
                        ErrorCodes.TYPE_CAS_NOT_FOUND
                ));
    }

    @Override
    public List<TypeCasDto> findAll() {

        return typeCasRepo.findAll().stream()
                .map(TypeCasDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            log.error("Impossible de supprimer type de cas avec un id nulle");
            return;
        }
        typeCasRepo.deleteById(id);
    }

    @Override
    public void disableTypeCas(Integer id) {
        TypeCas typeCas = typeCasRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Aucun type de cas avec l'ID = " + id,
                        ErrorCodes.GESTIONNAIRE_NOT_FOUND)
        );

        typeCas.setActive(false);
        typeCasRepo.save(typeCas);
    }
}
