package com.cas.sur.tout.urgents.service;

import com.cas.sur.tout.urgents.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto dto);

    ClientDto update(ClientDto dto);

    ClientDto findById(Long id);

    ClientDto findClientByTel(String tel);

    List<ClientDto> findAll();

    void deleteById(Long id);

    void disableClientById(Long id);

}
