package com.cas.sur.tout.urgents.service;

import com.cas.sur.tout.urgents.dto.OrganismeExtDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface OrganismeExtService {

    OrganismeExtDto save(OrganismeExtDto dto);

    OrganismeExtDto findById(Integer id);

    OrganismeExtDto findByName(String name);

    List<OrganismeExtDto> findAll();

    void deleteById(Integer id);

    void disableOrganismeById(Integer id);
}
