package com.cas.sur.tout.urgents.service;


import com.cas.sur.tout.urgents.dto.IncidentDto;
import com.cas.sur.tout.urgents.dto.UpdateIncidentStatusDto;

import java.util.List;

public interface IncidentService {

    IncidentDto save(IncidentDto dto);

    IncidentDto updateIncident(IncidentDto dto);

    List<IncidentDto> findAllByClientId(Long clientId);

    IncidentDto findById(Long id);

    List<IncidentDto> findAll();

    void deleteById(Long id);

    void updateStatus(UpdateIncidentStatusDto dto);

    void disableIncidentById(Long id);

}
