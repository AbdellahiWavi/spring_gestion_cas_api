package com.cas.sur.tout.urgents.controller.existingUser;

import com.cas.sur.tout.urgents.model.Gestionnaire;
import com.cas.sur.tout.urgents.repository.GestionnaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cas.sur.tout.urgents.utils.Constants.EXISTING_USER_ENDPOINT;

@RestController
@RequestMapping(EXISTING_USER_ENDPOINT)
public class ExistingUser {

    private final GestionnaireRepo userRepository;

    @Autowired
    public ExistingUser(GestionnaireRepo userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> userExists() {
        List<Gestionnaire> activeGestionnaire = userRepository.findUserByActive(true);
        boolean exists = !activeGestionnaire.isEmpty();
        return ResponseEntity.ok(exists);
    }
}

