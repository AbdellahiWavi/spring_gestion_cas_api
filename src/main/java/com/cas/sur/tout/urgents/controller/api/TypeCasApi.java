package com.cas.sur.tout.urgents.controller.api;

import com.cas.sur.tout.urgents.dto.TypeCasDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TypeCasApi {

    @GetMapping("/all")
    List<TypeCasDto> findAll();

    @PostMapping("/add")
    TypeCasDto save(@RequestBody TypeCasDto dto);

    @PutMapping("/update")
    TypeCasDto update(@RequestBody TypeCasDto dto);

    @GetMapping("/find/{id}")
    TypeCasDto findById(@PathVariable Integer id);

    @PutMapping("/isActive/{id}")
    void disableTypeCas(@PathVariable Integer id);

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Integer id);
}
