package com.cas.sur.tout.urgents.controller.api;


import com.cas.sur.tout.urgents.dto.IncidentDto;
import com.cas.sur.tout.urgents.dto.UpdateIncidentStatusDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IncidentApi {

    @GetMapping("/all")
    List<IncidentDto> findAll();

    @GetMapping("/find/{id}")
    IncidentDto findById(@PathVariable Long id);

    @GetMapping("/allByClient/{id}")
    List<IncidentDto> findAllByClientId(@PathVariable Long id);

    @PostMapping("/add")
    IncidentDto save(@RequestBody IncidentDto dto);

    @PutMapping("/update")
    IncidentDto updateIncident(@RequestBody IncidentDto dto);

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id);

    @PutMapping("/isActive/{id}")
    void disableIncidentById(@PathVariable Long id);

    @PutMapping("/status")
    void updateStatus(@RequestBody UpdateIncidentStatusDto dto);

}
