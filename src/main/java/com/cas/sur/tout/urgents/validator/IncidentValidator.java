package com.cas.sur.tout.urgents.validator;

import com.cas.sur.tout.urgents.dto.IncidentDto;

import java.util.ArrayList;
import java.util.List;

public class IncidentValidator {

    public static List<String> validate(IncidentDto incidentDto) {
        List<String> errors = new ArrayList<>();

        if (incidentDto == null) {
            errors.add("L'incident n'a pas complet!");
        }// else {
//
//            if (!StringUtils.hasLength(incidentDto.getDecrireAction())) {
//                errors.add("La description n'est pas valide!");
//            }
//        }
        return errors;
    }
}
