package flowfit.domain.user.domain.entity.member;
import flowfit.domain.schedule.domain.entity.Schedule;
import flowfit.domain.user.domain.entity.User;
import flowfit.domain.user.domain.entity.trainermember.TrainerMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.*;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User와 1:1 관계
    @OneToOne
    @JoinColumn(name = "userId", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainerMember> trainerMembers = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();
}
