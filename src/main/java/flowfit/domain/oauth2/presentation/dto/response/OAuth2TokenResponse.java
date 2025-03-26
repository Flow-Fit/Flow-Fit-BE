package flowfit.domain.oauth2.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OAuth2TokenResponse (
    @JsonProperty("access_token")
    String accessToken,

    @JsonProperty("refresh_token")
    String refreshToken,

    @JsonProperty("expires_in")
    Long expiresIn
) {}