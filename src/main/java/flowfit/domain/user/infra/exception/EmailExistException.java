package flowfit.domain.user.infra.exception;

import flowfit.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class EmailExistException extends FlowfitException {

    public EmailExistException() {
        super(HttpStatus.CONFLICT, "이메일이 존재합니다.");
    }
}
