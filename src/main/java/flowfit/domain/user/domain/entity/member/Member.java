package flowfit.domain.user.domain.entity.member;

import flowfit.domain.schedule.domain.entity.Schedule;
import flowfit.domain.user.domain.entity.Role;
import flowfit.domain.user.domain.entity.User;
import flowfit.domain.user.domain.entity.trainermember.TrainerMember;
import jakarta.persistence.*;
import lombok.Builder;
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

    private double height;   // 키 (단위: cm)

    private double weight;   // 체중 (단위: kg)


    private LocalDate birthDate;   // 생년월일 (형식: YYYY-MM-DD)

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainerMember> trainerMembers = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();



    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public void updateMemberInfo(String phoneNumber, double height, double weight, LocalDate birthDate) {
        this.phoneNumber = phoneNumber;
        this.height = height;
        this.weight = weight;
        this.birthDate = birthDate;
    }


}
