package flowfit.domain.ptrelation.domain.entity.prepare;

import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
public class PreparePt {

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
    @Builder
    public PreparePt(Trainer trainer, Member member) {
        this.trainer = trainer;
        this.member = member;
    }

}
