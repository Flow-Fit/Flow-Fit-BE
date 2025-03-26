package flowfit.flowfit.domain.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Role {
    MEMBER("ROLE_MEMBER"),
    TRAINER("ROLE_TRAINER"),
    ADMIN("ROLE_ADMIN");
    private final String key;

    Role(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    // Enum 매핑용 메서드
    public static Role from(String key) {
        return Arrays.stream(Role.values())
                .filter(r -> r.getKey().equalsIgnoreCase(key)) // 대소문자 구분 안함
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등급이 없네요.: " + key));
    }
}
