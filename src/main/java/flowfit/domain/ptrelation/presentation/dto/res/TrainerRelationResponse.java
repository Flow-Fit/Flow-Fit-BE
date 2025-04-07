package flowfit.domain.ptrelation.presentation.dto.res;

import flowfit.domain.ptrelation.domain.entity.ptrelation.PtFinal;
import java.time.LocalDate;

public record TrainerRelationResponse(
        Long id,
        String memberName,
        String alias,
        LocalDate ptStartDate,
        LocalDate ptLastDate,
        int totalMoney,
        int session,
        PtFinal ptFinal
) {
    public static TrainerRelationResponse of(
            Long id,
            String memberName,
            String alias,
            LocalDate ptStartDate,
            LocalDate ptLastDate,
            int totalMoney,
            int session,
            PtFinal ptFinal) {
        return new TrainerRelationResponse(id, memberName, alias, ptStartDate, ptLastDate, totalMoney, session, ptFinal);
    }
}