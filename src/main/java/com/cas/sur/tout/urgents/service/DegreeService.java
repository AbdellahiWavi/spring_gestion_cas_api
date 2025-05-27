package com.cas.sur.tout.urgents.service;

import com.cas.sur.tout.urgents.dto.DegreeDto;

import java.util.List;

public interface DegreeService {

    List<DegreeDto> findAll();

    DegreeDto save(DegreeDto dto);

    DegreeDto findById(Long id);

    void deleteById(Long id);

    void disableDegreeById(Long id);

}
