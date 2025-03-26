package flowfit.domain.oauth2.infra.exception;

import flowfit.global.infra.exception.auth.FlowfitAuthException;

import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends FlowfitAuthException {
    public InvalidRefreshTokenException() {
        super(HttpStatus.UNAUTHORIZED, "Refresh Token이 유효하지 않습니다.");
    }
}
