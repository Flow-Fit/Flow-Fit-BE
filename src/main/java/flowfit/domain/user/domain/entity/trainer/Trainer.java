package flowfit.domain.user.domain.entity.trainer;

import flowfit.domain.schedule.domain.entity.Schedule;
import flowfit.domain.user.domain.entity.Role;
import flowfit.domain.user.domain.entity.User;
import flowfit.domain.user.domain.entity.trainermember.TrainerMember;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.*;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "trainer")
@DiscriminatorValue("TRAINER")  // 부모 클래스에서의 타입 구분 값 설정
public class Trainer extends User {


    private String trainerCode;

    private String gymPlace;

    // 🔽 TrainerMember 양방향 관계
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainerMember> trainerMembers = new ArrayList<>();

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    @Builder
    public Trainer(String id, String email, String name, String profile, Role role, boolean status, String trainerCode, String gymPlace) {
        super(id, email, name, profile, role, status);  // 부모 클래스의 필드를 설정하는 부분 추가
        this.trainerCode = trainerCode;
        this.gymPlace = gymPlace;
    }

    public void updateGymPlace(String newGymPlace) {
        this.gymPlace = newGymPlace;
    }

    public void updateTrainerCode(String newTrainerCode) {
        this.trainerCode = newTrainerCode;
    }
}
