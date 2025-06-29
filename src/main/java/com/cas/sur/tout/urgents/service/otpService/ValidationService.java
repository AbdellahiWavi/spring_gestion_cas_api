package com.cas.sur.tout.urgents.service.otpService;

import com.cas.sur.tout.urgents.dto.otpVerification.ValidationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ValidationService {

    private final RestTemplate restTemplate;
    @Value("${chingui.validationKey}")
    private String validationKey;
    @Value("${chingui.validationToken}")
    private String token;

    public ValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Integer> sendValidationRequest(String phone) {
        String apiUrl = "https://chinguisoft.com/api/sms/validation/" + validationKey;

        // Préparation du corps JSON
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("phone", phone);
        requestBody.put("lang", "fr");

        // Préparation des headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Validation-token", token);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<ValidationResponse> response = restTemplate.postForEntity(apiUrl, request, ValidationResponse.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                int code = response.getBody().getCode();
                log.info("Code de validation reçu : {}", code);
                return Optional.of(code);
            } else {
                log.warn("Réponse non réussie ou corps vide : {}", response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi de la requête de validation : {}", e.getMessage(), e);
        }

        return Optional.empty();
    }

}

