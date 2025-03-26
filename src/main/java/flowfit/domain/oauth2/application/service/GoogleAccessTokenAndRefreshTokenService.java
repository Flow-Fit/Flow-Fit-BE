package flowfit.domain.oauth2.application.service;

import flowfit.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;

public interface GoogleAccessTokenAndRefreshTokenService {
    OAuth2TokenResponse getAccessTokenAndRefreshToken(String code);
    OAuth2TokenResponse refreshAccessToken(String refreshToken);
}
