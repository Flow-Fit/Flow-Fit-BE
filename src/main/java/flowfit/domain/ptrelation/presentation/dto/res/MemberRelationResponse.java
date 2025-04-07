package flowfit.domain.ptrelation.presentation.dto.res;

import flowfit.domain.ptrelation.domain.entity.ptrelation.PtFinal;

import java.time.LocalDate;

public record MemberRelationResponse(
        Long id,
        String trainerName,

        String code,

        LocalDate startTime,

        LocalDate startEndTime,

        PtFinal ptFinal
) {
    public static MemberRelationResponse of(Long id, String trainerName, String code, LocalDate startTime, LocalDate startEndTime, PtFinal ptFinal) {
        return new MemberRelationResponse(id, trainerName, code, startTime, startEndTime, ptFinal);
    }
}
