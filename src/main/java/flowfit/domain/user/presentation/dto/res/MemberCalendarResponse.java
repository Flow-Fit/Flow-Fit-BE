package flowfit.domain.user.presentation.dto.res;

import flowfit.domain.ptsession.domain.entity.Status;

import java.time.LocalDateTime;

public record MemberCalendarResponse(

        Long Id,
        LocalDateTime startTime,

        LocalDateTime endTime,
        Status status,

        String trainerName

) { public static MemberCalendarResponse of(Long id, LocalDateTime startTime, LocalDateTime endTime, Status status, String trainerName) {
    return new MemberCalendarResponse(id, startTime, endTime, status, trainerName);
}
}
