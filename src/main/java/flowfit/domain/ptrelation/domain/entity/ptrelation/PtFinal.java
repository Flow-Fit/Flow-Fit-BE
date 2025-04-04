package flowfit.domain.ptrelation.domain.entity.ptrelation;

import lombok.Getter;

import java.util.Arrays;

@Getter

public enum PtFinal {


    PREPARE("PT시작전"),
    PROCESS("진행"), // 세션 진행
    NCOMPLETED("미완료"), // PT 세션 모두 수행안했는데 기간 끝났을떄 완료(즉 뭐 고객이 환불을 한다던가 그럴떄)
    COMPLETED("완료"); // PT 세션을 모두 수행했으면 완료

    private final String key;

    PtFinal(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    // 한글 key 값으로 Status 매핑
    public static PtFinal from(String key) {
        return Arrays.stream(PtFinal.values())
                .filter(ptFinal -> ptFinal.key.equalsIgnoreCase(key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 상태입니다: " + key));
    }
}
