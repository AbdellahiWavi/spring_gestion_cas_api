package com.cas.sur.tout.urgents.controller.api;

import com.cas.sur.tout.urgents.dto.OrganismeExtDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrganismeExtApi {

    @GetMapping("/all")
    List<OrganismeExtDto> findAll();

    @PostMapping("/add")
    OrganismeExtDto save(@RequestBody OrganismeExtDto dto);

    @PutMapping("/update")
    OrganismeExtDto update(@RequestBody OrganismeExtDto dto);

    @GetMapping("/find/{id}")
    OrganismeExtDto findById(@PathVariable Integer id);

    @GetMapping("/orgByName/{name}")
    OrganismeExtDto findByName(@PathVariable String name);

    @PutMapping("/isActive/{id}")
    void disableOrgById(@PathVariable Integer id);

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Integer id);

}
