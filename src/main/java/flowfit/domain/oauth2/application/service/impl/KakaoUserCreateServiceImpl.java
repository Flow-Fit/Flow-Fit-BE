package flowfit.domain.oauth2.application.service.impl;

import flowfit.domain.oauth2.application.service.KakaoUserCreateService;
import flowfit.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
import flowfit.domain.oauth2.presentation.dto.response.OAuth2UserResponse;
import flowfit.domain.user.domain.entity.Role;
import flowfit.domain.user.domain.entity.User;
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

    @Override
    public Map<String, String> createKakaoUser(OAuth2TokenResponse oAuth2TokenResponse, OAuth2UserResponse oAuth2UserResponse) {
        User user = userRepository.findById(oAuth2UserResponse.id()).orElse(null);

        if(user == null) {
            log.info("신규 고객님, {} 님이 입장하셨습니다.", oAuth2UserResponse.name());
            user = User.builder()
                    .id(oAuth2UserResponse.id())
                    .email(oAuth2UserResponse.email())
                    .name(oAuth2UserResponse.name())
                    .profile(oAuth2UserResponse.profile())
                    .role(Role.TRAINER)
                    .build();
        }
        else {
            user.updateNameAndEmailAndProfile(oAuth2UserResponse.name(), oAuth2UserResponse.email(), oAuth2UserResponse.profile());
        }

        userRepository.save(user);

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
