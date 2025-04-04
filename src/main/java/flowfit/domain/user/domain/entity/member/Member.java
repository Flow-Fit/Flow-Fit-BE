package flowfit.domain.user.domain.entity.member;

import flowfit.domain.user.domain.entity.User;
import flowfit.domain.ptrelation.domain.entity.prepare.PreparePt;
import flowfit.domain.ptrelation.domain.entity.ptrelation.PtRelation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.*;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
@SuperBuilder
@Table(name = "member")
@DiscriminatorValue("MEMBER")  // 부모 클래스에서의 타입 구분 값 설정
public class Member extends User {

    private String phoneNumber;

    private String gender;

    private String target;     // 목표

    private double height;   // 키 (단위: cm)

    private double weight;   // 체중 (단위: kg)

    private LocalDate birthDate;   // 생년월일 (형식: YYYY-MM-DD)

    private boolean alarm; // 알람 받을지 안받을지(false면 안받고 true면 받음)

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PtRelation> ptRelations = new ArrayList<>();

    // 🔽 preparePt 양방향 관계
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreparePt> preparePts = new ArrayList<>();

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void updateHeight(String gender) {
        this.gender = gender;
    }
    public void updateHeight(double height) {
        this.height = height;
    }

    public void updateWeight(double weight) {
        this.weight = weight;
    }

    public void updateBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public void updateAlarm(boolean alarm) {
        this.alarm = alarm;
    }
    public void updateMemberInfo(String phoneNumber,String gender ,double height, double weight, LocalDate birthDate, boolean alarm) {
        this.phoneNumber = phoneNumber;
        this.gender=gender;
        this.height = height;
        this.weight = weight;
        this.birthDate = birthDate;
        this.alarm=alarm;
    }


}
