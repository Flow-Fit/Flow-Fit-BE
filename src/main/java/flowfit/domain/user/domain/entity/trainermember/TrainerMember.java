package flowfit.domain.user.domain.entity.trainermember;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "trainer_member")
public class TrainerMember {

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

    @Column(nullable = false)
    private LocalDateTime ptStartDate;
}
