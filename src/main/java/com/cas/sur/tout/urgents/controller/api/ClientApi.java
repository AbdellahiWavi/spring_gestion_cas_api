package com.cas.sur.tout.urgents.controller.api;

import com.cas.sur.tout.urgents.dto.ClientDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ClientApi {

    @PostMapping("/add")
    ClientDto save(@RequestBody ClientDto dto);

    @PutMapping("/update")
    ClientDto update(@RequestBody ClientDto dto);

    @GetMapping("/find/{id}")
    ClientDto findById(@PathVariable Long id);

    @PutMapping("/isActive/{id}")
    void disableClient(@PathVariable Long id);

    @GetMapping("/all")
    List<ClientDto> findAll();

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id);

}
