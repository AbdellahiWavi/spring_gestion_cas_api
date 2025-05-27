package com.cas.sur.tout.urgents.service.impl;

import com.cas.sur.tout.urgents.dto.GestionnaireDto;
import com.cas.sur.tout.urgents.dto.OrganismeExtDto;
import com.cas.sur.tout.urgents.exception.EntityNotFoundException;
import com.cas.sur.tout.urgents.exception.ErrorCodes;
import com.cas.sur.tout.urgents.exception.InvalidEntityException;
import com.cas.sur.tout.urgents.model.Gestionnaire;
import com.cas.sur.tout.urgents.model.OrganismeExt;
import com.cas.sur.tout.urgents.repository.OrganismeExtRepo;
import com.cas.sur.tout.urgents.service.OrganismeExtService;
import com.cas.sur.tout.urgents.validator.OrganismeExtValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class OrganismeExtServiceImpl implements OrganismeExtService {

    private final OrganismeExtRepo organismeExtRepo;
    @Value("${app.image-path-prefix}${app.default-image-name}")
    private String defaultImageUrl;

    @Autowired
    public OrganismeExtServiceImpl(OrganismeExtRepo organismeExtRepo) {
        this.organismeExtRepo = organismeExtRepo;
    }

    @Override
    public OrganismeExtDto save(OrganismeExtDto dto) {
        List<String> errors = OrganismeExtValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("l'organisme n'est valide {}", dto);
            throw new InvalidEntityException("l'organisme n'est pas valide", ErrorCodes.ORGANISME_NOT_VALID, errors);
        }
        dto.setActive(true);
        if (dto.getImage() == null || dto.getImage().isEmpty()) {
            dto.setImage(defaultImageUrl);
        }

        return OrganismeExtDto.fromEntity(
                organismeExtRepo.save(
                        OrganismeExtDto.toEntity(dto)
                ));
    }

    @Override
    public OrganismeExtDto findById(Integer id) {
        if (id == null) {
            log.error("l'id de l'organisme est nulle");
            return null;
        }
        return organismeExtRepo.findById(id)
                .map(OrganismeExtDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "l'organisme externe n'existe pas", ErrorCodes.ORGANISME_NOT_FOUND
                ));
    }

    @Override
    public List<OrganismeExtDto> findAll() {
        return organismeExtRepo.findAll().stream()
                .map(OrganismeExtDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            log.error("l'id de l'organisme exterieur est nulle");
            return;
        }

        organismeExtRepo.deleteById(id);
    }

    @Override
    public void disableOrganismeById(Integer id) {
        if (id == null) {
            log.error("L'ID de l'organisme à désactiver est nulle");
            return;
        }
        OrganismeExt organismeExt = organismeExtRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Aucun organisme avec l'ID = " + id,
                        ErrorCodes.GESTIONNAIRE_NOT_FOUND)
        );

        organismeExt.setActive(false);
        organismeExtRepo.save(organismeExt);
    }
}
