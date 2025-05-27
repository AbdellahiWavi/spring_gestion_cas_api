package com.cas.sur.tout.urgents.controller.api;

import com.cas.sur.tout.urgents.dto.DegreeDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface DegreeApi {

    @GetMapping("/all")
    List<DegreeDto> findAll();

    @PostMapping("/add")
    DegreeDto save(@RequestBody DegreeDto dto);

    @PutMapping("/update")
    DegreeDto update(@RequestBody DegreeDto dto);

    @GetMapping("/find/{id}")
    DegreeDto findById(@PathVariable Long id);

    @PutMapping("/isActive/{id}")
    void disableDegreeById(@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id);

}
