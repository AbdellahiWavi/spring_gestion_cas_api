package com.cas.sur.tout.urgents.service.impl;

import com.cas.sur.tout.urgents.dto.IncidentDto;
import com.cas.sur.tout.urgents.dto.UpdateIncidentStatusDto;
import com.cas.sur.tout.urgents.exception.EntityNotFoundException;
import com.cas.sur.tout.urgents.exception.ErrorCodes;
import com.cas.sur.tout.urgents.exception.InvalidEntityException;
import com.cas.sur.tout.urgents.model.*;
import com.cas.sur.tout.urgents.repository.*;
import com.cas.sur.tout.urgents.service.IncidentService;
import com.cas.sur.tout.urgents.validator.IncidentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepo incidentRepo;
    private final ClientRepo clientRepo;
    private final DegreeRepo degreeRepo;
    private final TypeCasRepo typeCasRepo;
    private final GestionnaireRepo gestionnaireRepo;

    @Autowired
    public IncidentServiceImpl(
           IncidentRepo incidentRepo, ClientRepo clientRepo, DegreeRepo degreeRepo,
           TypeCasRepo typeCasRepo, GestionnaireRepo gestionnaireRepo)
    {
        this.incidentRepo = incidentRepo;
        this.clientRepo = clientRepo;
        this.degreeRepo = degreeRepo;
        this.typeCasRepo = typeCasRepo;
        this.gestionnaireRepo = gestionnaireRepo;
    }

    @Override
    public IncidentDto save(IncidentDto dto) {
        List<String> errors = IncidentValidator.validate(dto);
        if(!errors.isEmpty()) {
            log.error("L'incident n'est pas valide {}", dto);
            throw new InvalidEntityException(
                    "L'incident n'est pas valid",
                    ErrorCodes.INCIDENT_NOT_VALID,
                    errors
            );
        }

        dto.setStatus(Status.EN_COURS);
        dto.setActive(true);
        Optional<Client> client = clientRepo.findById(dto.getClient().getId());
        if (client.isEmpty()) {
            log.warn("Le client avec l'id {} est introuvable dans la BDD", dto.getClient().getId());
            throw new EntityNotFoundException(
                    "Aucun client avec l'id " + dto.getClient().getId(),
                    ErrorCodes.CLIENT_NOT_FOUND
            );
        }

        Optional<Degree> degree = degreeRepo.findById(dto.getDegree().getId());
        if (degree.isEmpty()) {
            log.warn("Le degree avec l'id {} est introuvable dans la BDD", dto.getDegree().getId());
            throw new EntityNotFoundException(
                    "Aucune degree avec l'id " + dto.getDegree().getId(),
                    ErrorCodes.DEGREE_NOT_FOUND
            );
        }

        Optional<TypeCas> typeCas = typeCasRepo.findById(dto.getTypeCas().getId_cas());
        if (typeCas.isEmpty()) {
            log.warn("Le type de la cas avec l'id {} est introuvable dans la BDD", dto.getTypeCas().getId_cas());
            throw new EntityNotFoundException(
                    "Aucune type de la cas avec l'id " + dto.getTypeCas().getId_cas(),
                    ErrorCodes.TYPE_CAS_NOT_FOUND
            );
        }
        // TODO ajouter gestionnaire par defaut à chaque incident
//        Optional<Gestionnaire> gestionnaire = gestionnaireRepo.findById(dto.getGestionnaire().getId());
//        if (gestionnaire.isEmpty()) {
//            log.warn("Le gestionnaire avec l'id {} est introuvable dans la BDD", dto.getGestionnaire().getId());
//            throw new EntityNotFoundException(
//                    "Aucune gestionnaire avec l'id " + dto.getGestionnaire().getId(),
//                    ErrorCodes.GESTIONNAIRE_NOT_FOUND
//            );
//        }

        return IncidentDto.fromEntity(
                incidentRepo.save(
                        IncidentDto.toEntity(dto)
                )
        );
    }

    @Override
    public IncidentDto updateIncident(IncidentDto dto) {
        Incident incident = incidentRepo.findById(dto.getId()).orElseThrow(
                () -> new EntityNotFoundException(
                "Aucune Incident avec l'ID = " + dto.getId(),
                ErrorCodes.INCIDENT_NOT_FOUND)
        );
        Status oldStatus = incident.getStatus();

        // mise à jour des champs
        incident.setStatus(dto.getStatus());
        // ...

        if (!Objects.equals(oldStatus, dto.getStatus()) && dto.getStatus() == Status.TRAITE) {
            incident.setDateTraitement(LocalDateTime.now());
        }
        return IncidentDto.fromEntity(
                incidentRepo.save(IncidentDto.toEntity(dto))
        );
    }

    @Override
    public IncidentDto findById(Long id) {
        if (id == null){
            log.error("l'ID de l'incident nulle");
            return null;
        }
        return incidentRepo.findById(id)
                .map(IncidentDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune Incident avec l'ID = " + id,
                        ErrorCodes.INCIDENT_NOT_FOUND)
                );
    }

    @Override
    public List<IncidentDto> findAll() {
        return incidentRepo.findAll().stream()
                .map(IncidentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("L'ID de l'incident est nulle");
            return;
        }
        incidentRepo.deleteById(id);
    }

    @Override
    public void updateStatus(UpdateIncidentStatusDto dto) {
        if (dto == null) {
            log.error("L'objet à modifier est nulle");
            return;
        }

        Incident incident = incidentRepo.findById(dto.getId()).orElseThrow(
                () -> new EntityNotFoundException(
                        "Aucun Incident avec l'ID = " + dto.getId(),
                        ErrorCodes.INCIDENT_NOT_FOUND)
        );
        incident.setStatus(Status.valueOf(dto.getStatus()));
        incidentRepo.save(incident);

    }

    @Override
    public List<IncidentDto> findAllByClientId(Long clientId) {

        return incidentRepo.findAllByClientId(clientId).stream()
                .map(IncidentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void disableIncidentById(Long id) {
        if (id == null) {
            log.error("L'ID de l'incident à désactiver est nulle");
            return;
        }

        Incident incident = incidentRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Aucun Incident avec l'ID = " + id,
                        ErrorCodes.INCIDENT_NOT_FOUND)
        );
        incident.setActive(false);
        incidentRepo.save(incident);
    }

}
