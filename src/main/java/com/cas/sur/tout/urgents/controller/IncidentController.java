package com.cas.sur.tout.urgents.controller;

import com.cas.sur.tout.urgents.controller.api.IncidentApi;
import com.cas.sur.tout.urgents.dto.IncidentDto;
import com.cas.sur.tout.urgents.dto.UpdateIncidentStatusDto;
import com.cas.sur.tout.urgents.service.impl.IncidentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cas.sur.tout.urgents.utils.Constants.INCIDENT_ENDPOINT;

@RestController
@RequestMapping(INCIDENT_ENDPOINT)
public class IncidentController implements IncidentApi {

    private final IncidentServiceImpl incidentService;

    @Autowired
    public IncidentController(IncidentServiceImpl incidentService) {
        this.incidentService = incidentService;
    }

    @Override
    public List<IncidentDto> findAll() {
        return incidentService.findAll();
    }

    @Override
    public IncidentDto findById(Long id) {
        return incidentService.findById(id);
    }

    @Override
    public List<IncidentDto> findAllByClientId(Long id) {
        return incidentService.findAllByClientId(id);
    }

    @Override
    public void disableIncidentById(Long id) {
        incidentService.disableIncidentById(id);
    }

    @Override
    public void updateStatus(UpdateIncidentStatusDto dto) {
        incidentService.updateStatus(dto);
    }

    @Override
    public IncidentDto save(IncidentDto dto) {
        return incidentService.save(dto);
    }

    @Override
    public IncidentDto updateIncident(IncidentDto dto) {
        return incidentService.updateIncident(dto);
    }

    @Override
    public void deleteById(Long id) {
        incidentService.deleteById(id);
    }
}
