package com.cas.sur.tout.urgents.controller;

import com.cas.sur.tout.urgents.controller.api.ClientApi;
import com.cas.sur.tout.urgents.dto.ClientDto;
import com.cas.sur.tout.urgents.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cas.sur.tout.urgents.utils.Constants.CLIENT_ENDPOINT;

@RestController
@RequestMapping(CLIENT_ENDPOINT)
public class ClientController implements ClientApi {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto update(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto findById(Long id) {
        return clientService.findById(id);
    }

    @Override
    public void disableClient(Long id) {
        clientService.disableClientById(id);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        clientService.deleteById(id);
    }
}
