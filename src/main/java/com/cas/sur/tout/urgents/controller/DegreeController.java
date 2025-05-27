package com.cas.sur.tout.urgents.controller;

import com.cas.sur.tout.urgents.controller.api.DegreeApi;
import com.cas.sur.tout.urgents.dto.DegreeDto;
import com.cas.sur.tout.urgents.service.impl.DegreeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cas.sur.tout.urgents.utils.Constants.DEGREE_ENDPOINT;

@RestController
@RequestMapping(DEGREE_ENDPOINT)
public class DegreeController implements DegreeApi {

    private final DegreeServiceImpl degreeService;

    @Autowired
    public DegreeController(DegreeServiceImpl degreeService) {
        this.degreeService = degreeService;
    }

    @Override
    public List<DegreeDto> findAll() {
        return degreeService.findAll();
    }

    @Override
    public DegreeDto save(DegreeDto dto) {
        return degreeService.save(dto);
    }

    @Override
    public DegreeDto update(DegreeDto dto) {
        return degreeService.save(dto);
    }

    @Override
    public DegreeDto findById(Long id) {
        return degreeService.findById(id);
    }

    @Override
    public void disableDegreeById(Long id) {
        degreeService.disableDegreeById(id);
    }

    @Override
    public void deleteById(Long id) {
        degreeService.deleteById(id);
    }
}
