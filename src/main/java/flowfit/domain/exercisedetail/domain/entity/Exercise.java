package flowfit.domain.exercisedetail.domain.entity;


import flowfit.domain.schedule.domain.entity.Schedule;
import flowfit.domain.schedule.domain.entity.Status;
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
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    // 운동 이름
    @Column(nullable = false)
    private String exerciseName;

    @Column(nullable = false)
    private Integer reps;

    @Column(nullable = false)
    private Integer sets;

    @Column(nullable = false)
    private Float weight;

    // 운동 기간
    @Column(nullable = false)
    private Integer duration;

    // Schedule와 연관관계 (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
    @Builder
    public Exercise(String exerciseName, Integer reps, Integer sets, Float weight,
                    Integer duration, Schedule schedule) {
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.duration = duration;
        this.schedule = schedule;
    }

}