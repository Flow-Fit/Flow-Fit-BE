package flowfit.flowfit.domain.schedule.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter

public enum Status {
    PROPOSED("승인 대기"),                        // 트레이너가 제안한 상태 (승인 대기)
    SCHEDULED("승인 확정"),                       // PT가 확정된 상태 (회원 승인 또는 자동 확정)
    TRAINER_CANCELED_DEDUCTED("트레이너 취소(차감)"),     // 확정 상태에서 트레이너가 취소한 상태 (차감)
    TRAINER_CANCELED_NON_DEDUCTED("트레이너 취소(미차감)"), // 확정 상태에서 트레이너가 취소한 상태 (미차감)
    COMPLETED("완료"),                       // 완료된 상태 (회원 승인 혹은 자동 완료)
    DISPUTED("이의제기");                   // 회원이 완료/취소 상태에 대해 이의를 제기한 상태

    private final String key;

    Status(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    // 한글 key 값으로 Status 매핑
    public static Status from(String key) {
        return Arrays.stream(Status.values())
                .filter(status -> status.key.equalsIgnoreCase(key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 상태입니다: " + key));
    }
}
