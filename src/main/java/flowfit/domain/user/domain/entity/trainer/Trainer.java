package flowfit.domain.user.domain.entity.trainer;

import flowfit.domain.user.domain.entity.User;
import flowfit.domain.ptrelation.domain.entity.prepare.PreparePt;
import flowfit.domain.ptrelation.domain.entity.ptrelation.PtRelation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import java.util.*;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "trainer")
@SuperBuilder
@DiscriminatorValue("TRAINER")  // 부모 클래스에서의 타입 구분 값 설정
public class Trainer extends User {


    private String trainerCode;

    private String gymPlace;

    // 🔽 ptRelation 양방향 관계
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PtRelation> ptRelations = new ArrayList<>();

    // 🔽 preparePt 양방향 관계
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreparePt> preparePts = new ArrayList<>();




    public void updateGymPlace(String newGymPlace) {
        this.gymPlace = newGymPlace;
    }

    public void updateTrainerCode(String newTrainerCode) {
        this.trainerCode = newTrainerCode;
    }
}
