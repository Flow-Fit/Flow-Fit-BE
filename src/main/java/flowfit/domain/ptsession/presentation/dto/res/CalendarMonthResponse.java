package flowfit.domain.ptsession.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Builder
public class CalendarMonthResponse {

    private long totalIncome;
    private List<DailyIncome> monthlySchedules;

    @Getter
    @Builder
    public static class DailyIncome {
        private LocalDate date;
        private long income;
    }

    public static CalendarMonthResponse of(long total, Map<LocalDate, Long> incomeMap) {
        List<DailyIncome> daily = incomeMap.entrySet().stream()
                .map(e -> DailyIncome.builder().date(e.getKey()).income(e.getValue()).build())
                .collect(Collectors.toList());

        return CalendarMonthResponse.builder()
                .totalIncome(total)
                .monthlySchedules(daily)
                .build();

    }
}
