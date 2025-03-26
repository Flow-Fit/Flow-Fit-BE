package flowfit.domain.oauth2.infra.exception;


import flowfit.global.infra.exception.auth.FlowfitAuthException;
import org.springframework.http.HttpStatus;

public class DuplicateLoginException extends FlowfitAuthException {
    public DuplicateLoginException() {
        super(HttpStatus.UNAUTHORIZED, "중복 로그인은 허용되지 않습니다.");
    }
}
