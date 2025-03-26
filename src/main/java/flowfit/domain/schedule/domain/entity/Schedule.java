package flowfit.domain.schedule.domain.entity;

import flowfit.domain.exercisedetail.domain.entity.Exercise;
import flowfit.domain.user.domain.entity.Role;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String location;

    // 운동 목표
    @Column(nullable = false)
    private String traingTarget;

    @Column(nullable = false)
    private String comment;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // Trainer와 연관관계 (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    // Member와 연관관계 (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // exercise 연관관계
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();

    @Builder
    public Schedule(String location, String traingTarget, String comment, Status status,
                    Trainer trainer, Member member, List<Exercise> exercises) {
        this.location = location;
        this.traingTarget = traingTarget;
        this.comment = comment;
        this.status = status;
        this.trainer = trainer;
        this.member = member;
        this.exercises = exercises != null ? exercises : new ArrayList<>();
    }
}
