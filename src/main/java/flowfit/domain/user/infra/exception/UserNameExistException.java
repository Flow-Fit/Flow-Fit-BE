package flowfit.domain.user.infra.exception;

import flowfit.global.infra.exception.FlowfitException;
import flowfit.global.infra.exception.auth.FlowfitAuthException;
import org.springframework.http.HttpStatus;

public class UserNameExistException extends FlowfitException {

    public UserNameExistException() {
        super(HttpStatus.CONFLICT, "아이디가 이미 존재합니다.");
    }
}
