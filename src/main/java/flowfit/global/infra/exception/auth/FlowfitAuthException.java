package flowfit.global.infra.exception.auth;

import flowfit.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class FlowfitAuthException extends FlowfitException {
    public FlowfitAuthException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}