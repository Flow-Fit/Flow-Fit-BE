package flowfit.domain.user.infra.exception;

import flowfit.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class PasswordNotCorrectException extends FlowfitException {
    public PasswordNotCorrectException() {
        super(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
    }
}
