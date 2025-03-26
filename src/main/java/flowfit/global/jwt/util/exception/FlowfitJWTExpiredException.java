package flowfit.global.jwt.util.exception;


import flowfit.global.infra.exception.auth.FlowfitAuthException;
import org.springframework.http.HttpStatus;

public class FlowfitJWTExpiredException extends FlowfitAuthException {
    public FlowfitJWTExpiredException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
