package flowfit.domain.ptrelation.presentation.dto.req;


import flowfit.domain.ptrelation.domain.entity.ptrelation.PtFinal;

import java.time.LocalDateTime;

public record PtRelationRequestDto(
        String phoneNumber,

        LocalDateTime ptStartDate,
        LocalDateTime ptLastDate,
        int totalMoney,
        int session

) {
}
