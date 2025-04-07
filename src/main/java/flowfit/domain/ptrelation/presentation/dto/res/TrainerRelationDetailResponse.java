package flowfit.domain.ptrelation.presentation.dto.res;

import flowfit.domain.ptrelation.domain.entity.ptrelation.PtFinal;
import java.time.LocalDate;

public record TrainerRelationDetailResponse(
        Long id,
        String memberName,
        String memberContact,   // 회원 연락처
        LocalDate memberBirth,  // 생년월일
        // String memberNotes,     // 특이사항
        // String alias,           // 별명
        LocalDate ptStartDate,  // 등록일
        LocalDate ptLastDate,   // 만료일
        int remainingPt,        // 잔여 PT (총 PT - 사용한 PT)
        int session,            // 총 PT 횟수
        int unitPrice,          // 단가정보 (totalMoney / session)
        int totalMoney,         // 세션 총 금액
        PtFinal ptFinal         // PT 최종 상태
) {
    public static TrainerRelationDetailResponse of(
            Long id,
            String memberName,
            String memberContact,
            LocalDate memberBirth,
            // String memberNotes,
            // String alias,
            LocalDate ptStartDate,
            LocalDate ptLastDate,
            int remainingPt,
            int totalPt,
            int unitPrice,
            int totalMoney,
            PtFinal ptFinal) {
        return new TrainerRelationDetailResponse(
                id, memberName, memberContact, memberBirth, //memberNotes, alias,
                ptStartDate, ptLastDate, remainingPt, totalPt, unitPrice, totalMoney, ptFinal);
    }
}