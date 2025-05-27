package com.cas.sur.tout.urgents.controller.api;

import com.cas.sur.tout.urgents.dto.GestionnaireDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GestionnaiteApi {

    @GetMapping("/all")
    List<GestionnaireDto> findAll();

    @PostMapping("/add")
    GestionnaireDto save(@RequestBody GestionnaireDto dto);

    @PutMapping("/update")
    GestionnaireDto update(@RequestBody GestionnaireDto dto);

    @GetMapping("/find/{id}")
    GestionnaireDto findById(@PathVariable Long id);

    @PutMapping("/isActive/{id}")
    void disableUser(@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id);

}
