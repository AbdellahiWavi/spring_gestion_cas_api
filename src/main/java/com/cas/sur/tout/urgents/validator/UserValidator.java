package com.cas.sur.tout.urgents.validator;

import com.cas.sur.tout.urgents.dto.GestionnaireDto;
import com.cas.sur.tout.urgents.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validate(GestionnaireDto userDto) {
        List<String> errors = new ArrayList<>();

        if (userDto == null) {
            errors.add("Le nom n'est pas valide!");
            errors.add("L'email n'est pas valide!");
            errors.add("Le mot de passe n'est pas valide!");
        } else {

            if (!StringUtils.hasLength(userDto.getUsername())) {
                errors.add("Le nom n'est pas valide!");
            }

            if (!StringUtils.hasLength(userDto.getEmail())) {
                errors.add("L'email n'est pas valide!");
            }

            if (!StringUtils.hasLength(userDto.getPassword())) {
                errors.add("Le mot de passe n'est pas valid!");
            }
        }
        return errors;
    }
}
