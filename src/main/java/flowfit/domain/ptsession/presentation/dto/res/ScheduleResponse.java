package flowfit.domain.ptsession.presentation.dto.res;

import flowfit.domain.ptsession.domain.entity.PtSession;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleResponse {
    private Long sessionId;
    private String memberName;
    private String startTime;
    private String endTime;
    private String status;

    public static ScheduleResponse from(PtSession session) {
        return ScheduleResponse.builder()
                .sessionId(session.getId())
                .memberName(session.getPtRelation().getMember().getName())
                .startTime(session.getStartTime().toLocalTime().toString())
                .endTime(session.getEndTime().toLocalTime().toString())
                .status(session.getStatus().name())
                .build();
    }
}