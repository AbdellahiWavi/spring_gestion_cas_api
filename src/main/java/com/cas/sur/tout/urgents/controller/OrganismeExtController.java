package com.cas.sur.tout.urgents.controller;

import com.cas.sur.tout.urgents.controller.api.OrganismeExtApi;
import com.cas.sur.tout.urgents.dto.OrganismeExtDto;
import com.cas.sur.tout.urgents.service.impl.OrganismeExtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cas.sur.tout.urgents.utils.Constants.ORGANISME_EXTERIEUR_ENDPOINT;

@RestController
@RequestMapping(ORGANISME_EXTERIEUR_ENDPOINT)
public class OrganismeExtController implements OrganismeExtApi {

    private final OrganismeExtServiceImpl organismeExtService;

    @Autowired
    public OrganismeExtController(OrganismeExtServiceImpl organismeExtService) {
        this.organismeExtService = organismeExtService;
    }

    @Override
    public List<OrganismeExtDto> findAll() {
        return organismeExtService.findAll();
    }

    @Override
    public OrganismeExtDto save(OrganismeExtDto dto) {
        return organismeExtService.save(dto);
    }

    @Override
    public OrganismeExtDto update(OrganismeExtDto dto) {
        return organismeExtService.save(dto);
    }

    @Override
    public OrganismeExtDto findById(Integer id) {
        return organismeExtService.findById(id);
    }

    @Override
    public void disableOrgById(Integer id) {
        organismeExtService.disableOrganismeById(id);
    }

    @Override
    public void deleteById(Integer id) {
        organismeExtService.deleteById(id);
    }
}
