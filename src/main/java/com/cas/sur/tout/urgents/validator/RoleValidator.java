package com.cas.sur.tout.urgents.validator;

import com.cas.sur.tout.urgents.dto.RoleDto;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {

    public static List<String> validate(RoleDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("Veuillez renseigner toutes les champs");

        }
        return errors;
    }
}
