package flowfit.domain.ptrelation.presentation.dto.req;


import flowfit.domain.ptrelation.domain.entity.ptrelation.PtFinal;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PtRelationRequestDto(
        String phoneNumber,

        LocalDate ptStartDate,
        LocalDate ptLastDate,
        int totalMoney,
        int session

) {
}
