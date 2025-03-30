package flowfit.domain.oauth2.application.service.impl;

import flowfit.domain.oauth2.application.service.KakaoUserCreateService;
import flowfit.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
import flowfit.domain.oauth2.presentation.dto.response.OAuth2UserResponse;
import flowfit.domain.user.domain.entity.Role;
import flowfit.domain.user.domain.entity.User;
import flowfit.domain.user.domain.entity.trainer.Trainer;
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

    @Override
    public Map<String, String> createKakaoUser(OAuth2TokenResponse oAuth2TokenResponse, OAuth2UserResponse oAuth2UserResponse) {
        log.info("사용자 생성 시작: oAuth2UserResponse.id()={}", oAuth2UserResponse.id());

        // null 체크 추가
        if (oAuth2UserResponse.id() == null || oAuth2UserResponse.id().isEmpty()) {
            log.error("카카오에서 반환된 사용자 ID가 null 또는 빈 문자열입니다");
            throw new IllegalArgumentException("사용자 ID가 유효하지 않습니다");
        }

        User user = userRepository.findById(oAuth2UserResponse.id()).orElse(null);

        if(user == null) {
            log.info("신규 고객님, {} 님이 입장하셨습니다.", oAuth2UserResponse.getName());
            log.info("신규 고객님, {} 님이 입장하셨습니다.", oAuth2UserResponse.getEmail());
            log.info("신규 고객님, {} 님이 입장하셨습니다.", oAuth2UserResponse.getProfile());
            user = User.builder()
                    .id(oAuth2UserResponse.id())
                    .email(oAuth2UserResponse.getEmail())  // 메소드 호출 방식으로 변경
                    .name(oAuth2UserResponse.getName())    // 메소드 호출 방식으로 변경
                    .profile(oAuth2UserResponse.getProfile())  // 메소드 호출 방식으로 변경
                    .role(Role.TRAINER)
                    .build();
            userRepository.save(user);
            trainerRepository.save(new Trainer(user));
        }
        else {
            user.updateNameAndEmailAndProfile(oAuth2UserResponse.getName(), oAuth2UserResponse.getEmail(), oAuth2UserResponse.getProfile());
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
