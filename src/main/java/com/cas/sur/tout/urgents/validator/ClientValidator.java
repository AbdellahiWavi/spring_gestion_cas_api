package com.cas.sur.tout.urgents.validator;

import com.cas.sur.tout.urgents.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();

        if (clientDto == null) {
            errors.add("Le nom ne peux pas etre vide!");
            errors.add("Le prenom ne peux pas etre vide!");
            errors.add("Le numero de telephone est obligatoire!");
        } else {
            if (!StringUtils.hasLength(clientDto.getUsername())) {
                errors.add("Le nom ne peux pas etre vide!");
            }
            if (!StringUtils.hasLength(clientDto.getTel())) {
                errors.add("Le numero de telephone est obligatoire!");
            }
        }
        return errors;
    }
}
