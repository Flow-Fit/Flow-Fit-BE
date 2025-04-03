package flowfit.domain.oauth2.application.service.impl;

import flowfit.domain.oauth2.application.service.KakaoUserCreateService;
import flowfit.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
import flowfit.domain.oauth2.presentation.dto.response.OAuth2UserResponse;
import flowfit.domain.user.domain.entity.Role;
import flowfit.domain.user.domain.entity.User;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import flowfit.domain.user.domain.repository.MemberRepository;
import flowfit.domain.user.domain.repository.TrainerRepository;
import flowfit.domain.user.domain.repository.UserRepository;
import flowfit.global.jwt.domain.entity.KakaoJsonWebToken;
import flowfit.global.jwt.domain.repository.KakaoJsonWebTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KakaoUserCreateServiceImpl implements KakaoUserCreateService {

    private final UserRepository userRepository;
    private final KakaoJsonWebTokenRepository KakaoJsonWebTokenRepository;
    private final TrainerRepository trainerRepository;
    private final MemberRepository memberRepository;

    @Override
    public Map<String, String> createKakaoUser(OAuth2TokenResponse oAuth2TokenResponse, OAuth2UserResponse oAuth2UserResponse, String type) {

        User user;
        // null 체크 추가
        if (oAuth2UserResponse.id() == null || oAuth2UserResponse.id().isEmpty()) {
            log.error("카카오에서 반환된 사용자 ID가 null 또는 빈 문자열입니다");
            throw new IllegalArgumentException("사용자 ID가 유효하지 않습니다");
        }
        // 트레이너
        if (type.equals(Role.TRAINER.name())) {
            user = userRepository.findById("TRAINER " + oAuth2UserResponse.id()).orElse(null);
            if (user == null) {

                Trainer trainer = Trainer.builder()
                        .id("TRAINER " + oAuth2UserResponse.id()) // Trainer의 ID 형식 지정
                        .email(oAuth2UserResponse.getEmail())
                        .name(oAuth2UserResponse.getName())
                        .profile(oAuth2UserResponse.getProfile())
                        .role(Role.TRAINER)
                        .status(true)
                        .build();

                trainerRepository.save(trainer);

            } else {
                user.updateNameAndEmailAndProfile(oAuth2UserResponse.getName(), oAuth2UserResponse.getEmail(), oAuth2UserResponse.getProfile());
            }
            // 회원
        } else {
            user = userRepository.findById("MEMBER " + oAuth2UserResponse.id()).orElse(null);
            if (user == null) {

                Member member = Member.builder()
                        .id("MEMBER " + oAuth2UserResponse.id()) // Trainer의 ID 형식 지정
                        .email(oAuth2UserResponse.getEmail())
                        .name(oAuth2UserResponse.getName())
                        .profile(oAuth2UserResponse.getProfile())
                        .role(Role.MEMBER)
                        .status(false)
                        .build();

                memberRepository.save(member);

            } else {
                user.updateNameAndEmailAndProfile(oAuth2UserResponse.getName(), oAuth2UserResponse.getEmail(), oAuth2UserResponse.getProfile());
            }
        }


        LocalDateTime now = LocalDateTime.now().plusSeconds(oAuth2TokenResponse.expiresIn());

        KakaoJsonWebToken kakaoJsonWebToken = KakaoJsonWebToken.builder()
                .userId(user.getId())
                .accessToken(oAuth2TokenResponse.accessToken())
                .refreshToken(oAuth2TokenResponse.refreshToken())
                .expiresIn(now)
                .build();

        KakaoJsonWebTokenRepository.deleteById(user.getId());
        KakaoJsonWebTokenRepository.save(kakaoJsonWebToken);

        return Map.of(
                "id", user.getId(),
                "role", user.getRole().toString(),
                "email", user.getEmail()
        );
    }
}
