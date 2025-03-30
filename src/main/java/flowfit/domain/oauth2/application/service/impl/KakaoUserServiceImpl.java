package flowfit.domain.oauth2.application.service.impl;

import flowfit.domain.oauth2.application.service.KakaoUserService;
import flowfit.domain.oauth2.presentation.dto.response.OAuth2UserResponse;
import flowfit.global.infra.feignclient.KakaoOAuth2UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoUserServiceImpl implements KakaoUserService {

    private final KakaoOAuth2UserFeignClient KakaoOAuth2UserFeignClient;

    @Override
    public OAuth2UserResponse getUser(String accessToken) {
        return KakaoOAuth2UserFeignClient.getUserInfo("Bearer " + accessToken);
    }
}
