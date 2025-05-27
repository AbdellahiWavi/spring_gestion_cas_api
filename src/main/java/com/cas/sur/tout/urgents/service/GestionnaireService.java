package com.cas.sur.tout.urgents.service;

import com.cas.sur.tout.urgents.dto.GestionnaireDto;

import java.util.List;

public interface GestionnaireService {

    GestionnaireDto save(GestionnaireDto dto);

    GestionnaireDto update(GestionnaireDto dto);

    GestionnaireDto findById(Long id);

    GestionnaireDto findUserByEmail(String email);

    List<GestionnaireDto> findAll();

    void deleteById(Long id);

    void disableUser(Long id);
}
