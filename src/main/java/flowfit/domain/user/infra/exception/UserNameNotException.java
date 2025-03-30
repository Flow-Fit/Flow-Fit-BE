package flowfit.domain.user.infra.exception;

import flowfit.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class UserNameNotException extends FlowfitException {
    public UserNameNotException() {
        super(HttpStatus.UNAUTHORIZED, "아이디가 일치하지 않습니다.");
    }
}
