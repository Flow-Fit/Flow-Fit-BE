package flowfit.flowfit.domain.user.domain.entity;


import flowfit.flowfit.domain.user.domain.entity.member.Member;
import flowfit.flowfit.domain.user.domain.entity.trainer.Trainer;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
public class User {
    @Id
    @Column(unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private String email;

    //아이디
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // 이름
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    private String phoneNumber;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    // 🔽 Member와 1:1 관계 (양방향)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Member member;

    // 🔽 Trainer와 1:1 관계 (양방향)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Trainer trainer;

    @Builder
    public User(String id, String email, String username, String password, String name,
                String profileImage, String phoneNumber, Role role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

}
