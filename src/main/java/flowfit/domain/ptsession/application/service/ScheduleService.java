package flowfit.domain.ptsession.application.service;

import flowfit.domain.ptsession.presentation.dto.req.ScheduleRequest;
import flowfit.domain.ptsession.presentation.dto.res.CalendarMonthResponse;
import flowfit.domain.ptsession.presentation.dto.res.ScheduleResponse;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    Long createSession(ScheduleRequest request);

    void updateSession(Long sessionId, ScheduleRequest request);

    void deleteSession(Long sessionId, String reason, boolean charge);

    CalendarMonthResponse getMonthlySummary(int year, int month);

    List<ScheduleResponse> getSessionsByDate(LocalDate date);
}
