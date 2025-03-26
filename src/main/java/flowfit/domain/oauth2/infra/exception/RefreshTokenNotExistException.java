package flowfit.domain.oauth2.infra.exception;

import flowfit.global.infra.exception.auth.FlowfitAuthException;

import org.springframework.http.HttpStatus;

public class RefreshTokenNotExistException extends FlowfitAuthException {
    public RefreshTokenNotExistException() {
        super(HttpStatus.UNAUTHORIZED, "Refresh Token이 DB에 존재하지 않습니다.");
    }
}
