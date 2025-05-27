package com.cas.sur.tout.urgents.validator;

import com.cas.sur.tout.urgents.dto.TypeCasDto;

import java.util.ArrayList;
import java.util.List;

public class TypeCasValidator {

    public static List<String> validate(TypeCasDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("le type ne peux pas etre vide");
        }
        return errors;
    }
}
