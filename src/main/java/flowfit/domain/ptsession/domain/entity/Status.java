package flowfit.domain.ptsession.domain.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter

public enum Status {

    CANCEL_M("회원 취소(차감)"),     // 취소1
    CANCEL_T("트레이너 취소(미차감)"),     // 취소2
    PROCESS("진행"), // 세션 진행
    CONFLICT("이의제기"), // 세션 이의 제기 즉 세션 진행과 완료 사이
    COMPLETED("완료"); // 세션 이의 제기 까지 끝났으면 완료

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
