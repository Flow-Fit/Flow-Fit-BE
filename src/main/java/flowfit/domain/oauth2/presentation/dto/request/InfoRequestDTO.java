package flowfit.domain.oauth2.presentation.dto.request;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record InfoRequestDTO(
        String type,

        String gymPlace,

        String phoneNumber,
        double height,

        double weight,
        LocalDate birthDate
) {
}
