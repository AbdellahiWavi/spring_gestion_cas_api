package com.cas.sur.tout.urgents.service.auth;

import com.cas.sur.tout.urgents.exception.EntityNotFoundException;
import com.cas.sur.tout.urgents.exception.ErrorCodes;
import com.cas.sur.tout.urgents.model.Client;
import com.cas.sur.tout.urgents.model.RefreshToken;
import com.cas.sur.tout.urgents.repository.ClientRepo;
import com.cas.sur.tout.urgents.repository.RefreshTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepo refreshTokenRepository;

    @Autowired
    private ClientRepo clientRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long id) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByClientId(id);
        // Supprimer l'ancien si existant
        if (refreshToken.isPresent()) {
            refreshTokenRepository.deleteByClientId(id);
        }

        Client client = clientRepository.findById(id).orElseThrow(
                () -> new  EntityNotFoundException(
                    "Utilisateur n'est pas trouvée veuillez connecter",
                    ErrorCodes.USER_NOT_FOUND
                )
        );
        return refreshTokenRepository.save(
                RefreshToken.builder()
                        .client(client)
                        .expiryDate(Instant.now().plus(7, ChronoUnit.DAYS))
                        .token(UUID.randomUUID().toString())
                        .build()
        );
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token est expirée. Veuillez connecter une fois plus.");
        }
        return token;
    }
}
