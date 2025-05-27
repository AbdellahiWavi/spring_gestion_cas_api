package com.cas.sur.tout.urgents.validator;

import com.cas.sur.tout.urgents.dto.DegreeDto;

import java.util.ArrayList;
import java.util.List;

public class DegreeValidator {

    public static List<String> validate(DegreeDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("Veuillez rensigner le type de la degrée");
        }

        return errors;
    }
}
