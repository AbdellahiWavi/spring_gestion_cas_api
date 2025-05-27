package com.cas.sur.tout.urgents.controller;

import com.cas.sur.tout.urgents.controller.api.GestionnaiteApi;
import com.cas.sur.tout.urgents.dto.GestionnaireDto;
import com.cas.sur.tout.urgents.service.impl.GestionnaireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cas.sur.tout.urgents.utils.Constants.GESTIONNAIRE_ENDPOINT;

@RestController
@RequestMapping(GESTIONNAIRE_ENDPOINT)
public class GestionnaireController implements GestionnaiteApi {

    private final GestionnaireServiceImpl gestionnaireService;

    @Autowired
    public GestionnaireController(GestionnaireServiceImpl gestionnaireService) {
        this.gestionnaireService = gestionnaireService;
    }

    @Override
    public List<GestionnaireDto> findAll() {
        return gestionnaireService.findAll();
    }

    @Override
    public GestionnaireDto save(GestionnaireDto dto) {
        return gestionnaireService.save(dto);
    }

    @Override
    public GestionnaireDto update(GestionnaireDto dto) {
        return gestionnaireService.update(dto);
    }

    @Override
    public GestionnaireDto findById(Long id) {
        return gestionnaireService.findById(id);
    }

    @Override
    public void disableUser(Long id) {
        gestionnaireService.disableUser(id);
    }

    @Override
    public void deleteById(Long id) {
        gestionnaireService.deleteById(id);
    }
}
