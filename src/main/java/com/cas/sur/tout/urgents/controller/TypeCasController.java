package com.cas.sur.tout.urgents.controller;

import com.cas.sur.tout.urgents.controller.api.TypeCasApi;
import com.cas.sur.tout.urgents.dto.TypeCasDto;
import com.cas.sur.tout.urgents.service.impl.TypeCasServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cas.sur.tout.urgents.utils.Constants.TYPE_CAS_ENDPOINT;

@RestController
@RequestMapping(TYPE_CAS_ENDPOINT)
public class TypeCasController implements TypeCasApi {

    private final TypeCasServiceImpl typeCasService;

    @Autowired
    public TypeCasController(TypeCasServiceImpl typeCasService) {
        this.typeCasService = typeCasService;
    }

    @Override
    public List<TypeCasDto> findAll() {
        return typeCasService.findAll();
    }

    @Override
    public TypeCasDto save(TypeCasDto dto) {
        return typeCasService.save(dto);
    }

    @Override
    public TypeCasDto update(TypeCasDto dto) {
        return typeCasService.save(dto);
    }

    @Override
    public TypeCasDto findById(Integer id) {
        return typeCasService.findById(id);
    }

    @Override
    public void disableTypeCas(Integer id) {
        typeCasService.disableTypeCas(id);
    }

    @Override
    public void deleteById(Integer id) {
        typeCasService.deleteById(id);
    }
}
