package flowfit.domain.ptsession.application.service.impl;

import flowfit.domain.ptrelation.domain.entity.ptrelation.PtRelation;
import flowfit.domain.ptrelation.domain.repository.PtRelationRepository;
import flowfit.domain.ptsession.application.service.ScheduleService;
import flowfit.domain.ptsession.domain.entity.PtSession;
import flowfit.domain.ptsession.domain.entity.Status;
import flowfit.domain.ptsession.domain.repository.PtSessionRepository;
import flowfit.domain.ptsession.presentation.dto.req.ScheduleRequest;
import flowfit.domain.ptsession.presentation.dto.res.CalendarMonthResponse;
import flowfit.domain.ptsession.presentation.dto.res.ScheduleResponse;
import flowfit.domain.user.infra.exception.PtSessionNotFoundException;
import flowfit.domain.user.infra.exception.PtRelationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final PtSessionRepository sessionRepository;
    private final PtRelationRepository relationRepository;

    @Override
    public Long createSession(ScheduleRequest req) {
        PtRelation relation = relationRepository.findById(req.getRelationId())
                .orElseThrow(PtRelationNotFoundException::new);

        LocalDateTime start = LocalDateTime.parse(req.getDate() + "T" + req.getStartTime());
        LocalDateTime end = LocalDateTime.parse(req.getDate() + "T" + req.getEndTime());

        PtSession session = PtSession.builder()
                .ptRelation(relation)
                .startTime(start)
                .endTime(end)
                .status(Status.PROCESS)
                .message(req.getMessage())
                .build();

        return sessionRepository.save(session).getId();
    }

    @Override
    public void updateSession(Long sessionId, ScheduleRequest req) {
        PtSession session = sessionRepository.findById(sessionId)
                .orElseThrow(PtSessionNotFoundException::new);

        session.updateStartTime(LocalDateTime.parse(req.getDate() + "T" + req.getStartTime()));
        session.updateEndTime(LocalDateTime.parse(req.getDate() + "T" + req.getEndTime()));
        session.updateMessage(req.getMessage());
    }

    @Override
    public void deleteSession(Long sessionId, String reason, boolean charge) {
        PtSession session = sessionRepository.findById(sessionId)
                .orElseThrow(PtSessionNotFoundException::new);
        session.updateStatus(Status.CANCEL_T); // TODO: 사유 분기처리
    }

    @Override
    public List<ScheduleResponse> getSessionsByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59);

        String trainerId = getCurrentTrainerId();

        return sessionRepository
                .findAllByPtRelation_Trainer_IdAndStartTimeBetween(trainerId, startOfDay, endOfDay)
                .stream()
                .map(ScheduleResponse::from)
                .collect(toList());
    }

    private String getCurrentTrainerId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public CalendarMonthResponse getMonthlySummary(int year, int month) {
        String trainerId = getCurrentTrainerId();
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<PtSession> sessions = sessionRepository
                .findAllByPtRelation_Trainer_IdAndStartTimeBetween(trainerId, start.atStartOfDay(), end.atTime(23, 59));

        // 일 수입을 구하고 더하는 식으로 월 수입 도출
        Map<LocalDate, Long> dailyIncome = new TreeMap<>();
        long total = 0L;

        for (PtSession s : sessions) {
            if (s.getStatus() == Status.PROCESS || s.getStatus() == Status.COMPLETED) {
                LocalDate day = s.getStartTime().toLocalDate();
                long income = s.getPtRelation().getTotalMoney() / s.getPtRelation().getSession();
                dailyIncome.put(day, dailyIncome.getOrDefault(day, 0L) + income);
                total += income;
            }
        }

        return CalendarMonthResponse.of(total, dailyIncome);
    }
}