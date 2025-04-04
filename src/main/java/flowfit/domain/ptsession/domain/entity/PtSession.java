package flowfit.domain.ptsession.domain.entity;

import flowfit.domain.user.domain.entity.ptrelation.PtRelation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "pt_session")
public class PtSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    // 일정 취소 사유
    @Column
    private String message;

    // 세션 상태(취소, 진행, 이의제기 ,완료)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // 이의제기 (회원이 작성하는 내용)
    private String conflict;

    // Trainer와 연관관계 (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_relation_id", nullable = false)
    private PtRelation ptRelation;


    // === 빌더 패턴 추가 ===
    @Builder
    public PtSession(LocalDateTime startTime, LocalDateTime endTime, String message, Status status,
                     String conflict, PtRelation ptRelation) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.message = message;
        this.status = status;
        this.conflict = conflict;
        this.ptRelation = ptRelation;
    }

    // === 업데이트 메서드들 ===

    public void updateMessage(String message) {
        this.message = message;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void updateConflict(String conflict) {
        this.conflict = conflict;
    }

    public void updateStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void updateEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
