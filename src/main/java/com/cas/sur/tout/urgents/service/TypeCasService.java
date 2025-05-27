package com.cas.sur.tout.urgents.service;

import com.cas.sur.tout.urgents.dto.TypeCasDto;

import java.util.List;

public interface TypeCasService {

    TypeCasDto save(TypeCasDto dto);

    TypeCasDto findById(Integer id);

    List<TypeCasDto> findAll();

    void deleteById(Integer id);

    void disableTypeCas(Integer id);

}
