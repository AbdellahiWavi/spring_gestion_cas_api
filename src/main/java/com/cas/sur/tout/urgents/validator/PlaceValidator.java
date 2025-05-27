package com.cas.sur.tout.urgents.validator;

import com.cas.sur.tout.urgents.dto.ZoneDto;

import java.util.ArrayList;
import java.util.List;

public class PlaceValidator {

    public static List<String> validate(ZoneDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("L'emplacement saisit est incorrecte");
        }

        return errors;
    }
}
