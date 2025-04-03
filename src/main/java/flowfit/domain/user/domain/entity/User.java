package flowfit.domain.user.domain.entity;


import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)  // 상속 전략을 JOINED로 설정
@DiscriminatorColumn(name = "user_type")  // 자식 클래스 구분을 위한 컬럼
public class User {
    @Id
    @Column(unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private String email;

    // 이름
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String profile;

    @Column(nullable = false)
    private boolean status;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Builder
    public User(String id, String email, String name,
                String profile, Role role, boolean status) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profile = profile;
        this.role = role;
        this.status= status;
    }
    public void updateNameAndEmailAndProfile(String name, String email, String profile) {
        this.name = name;
        this.email = email;
        this.profile = profile;
    }

    public void updateStatus(boolean status) {
        this.status = status;
    }
}
