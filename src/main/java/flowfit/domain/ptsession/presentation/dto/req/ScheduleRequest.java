package flowfit.domain.ptsession.presentation.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequest {
    private Long relationId;
    private String date;       // "2025-01-28"
    private String startTime;  // "19:00"
    private String endTime;    // "20:00"
    private String message;    // optional
}