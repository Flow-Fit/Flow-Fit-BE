package flowfit.domain.ptsession.presentation.controller;

import flowfit.domain.ptsession.application.service.ScheduleService;
import flowfit.domain.ptsession.presentation.dto.req.ScheduleRequest;
import flowfit.domain.ptsession.presentation.dto.res.CalendarMonthResponse;
import flowfit.domain.ptsession.presentation.dto.res.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sessions")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ScheduleRequest request) {
        Long id = scheduleService.createSession(request);
        return ResponseEntity.ok().body(id);
    }

    @PatchMapping("/{sessionId}")
    public ResponseEntity<?> update(@PathVariable Long sessionId, @RequestBody ScheduleRequest request) {
        scheduleService.updateSession(sessionId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<?> delete(@PathVariable Long sessionId,
                                    @RequestParam String reason,
                                    @RequestParam boolean charge) {
        scheduleService.deleteSession(sessionId, reason, charge);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = {"year", "month"})
    public ResponseEntity<CalendarMonthResponse> getMonthly(@RequestParam int year, @RequestParam int month) {
        return ResponseEntity.ok(scheduleService.getMonthlySummary(year, month));
    }

    @GetMapping(params = "date")
    public ResponseEntity<List<ScheduleResponse>> getDaily(@RequestParam String date) {
        return ResponseEntity.ok(scheduleService.getSessionsByDate(LocalDate.parse(date)));
    }
}
