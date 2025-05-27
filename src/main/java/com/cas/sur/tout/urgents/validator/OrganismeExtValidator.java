package com.cas.sur.tout.urgents.validator;

import com.cas.sur.tout.urgents.dto.OrganismeExtDto;

import java.util.ArrayList;
import java.util.List;

public class OrganismeExtValidator {

    public static List<String> validate(OrganismeExtDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("l'organisme n'est pas valide");
        }
        return errors;
    }
}
