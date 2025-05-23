package flowfit.domain.ptrelation.domain.entity.ptrelation;

import flowfit.domain.ptsession.domain.entity.PtSession;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "pt_relation")
public class PtRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 트레이너와 다대일
    @ManyToOne
    @JoinColumn(name = "trainerId", nullable = false)
    private Trainer trainer;

    // 멤버와 다대일
    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    // 등록일
    @Column(nullable = false)
    private LocalDate ptStartDate;

    // 만료일
    @Column(nullable = false)
    private LocalDate ptLastDate;


    // pt 최종상태(완료(PT수행 모두), 진행, 시작전, 미완료(PT 수행 취소) )
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PtFinal ptFinal;

    // 세션 총 금액
    @Column(nullable = false)
    private int totalMoney;

    // 세션 횟수
    @Column(nullable = false)
    private int session;

    // 고객 별명
    private String alias;

    @OneToMany(mappedBy = "ptRelation", cascade = CascadeType.ALL)
    private List<PtSession> ptSessions = new ArrayList<>();


    // === 빌더 패턴 추가 ===
    @Builder
    public PtRelation(Trainer trainer, Member member, LocalDate ptStartDate, LocalDate ptLastDate,
                      PtFinal ptFinal, int totalMoney, int session) {
        this.trainer = trainer;
        this.member = member;
        this.ptStartDate = ptStartDate;
        this.ptLastDate = ptLastDate;
        this.ptFinal = ptFinal;
        this.totalMoney = totalMoney;
        this.session = session;
    }

    // === 업데이트 메서드들 ===

    public void updatePtStartDate(LocalDate ptStartDate) {
        this.ptStartDate = ptStartDate;
    }

    public void updatePtLastDate(LocalDate ptLastDate) {
        this.ptLastDate = ptLastDate;
    }

    public void updateTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void updateSession(int session) {
        this.session = session;
    }

    public void updateAlias(String alias) {
        this.alias = alias;
    }

    public void updatePtFinal(PtFinal ptFinal) {
        this.ptFinal = ptFinal;
    }
}
