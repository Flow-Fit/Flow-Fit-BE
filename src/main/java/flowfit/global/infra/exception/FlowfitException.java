package flowfit.global.infra.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FlowfitException extends RuntimeException {

    private final HttpStatus status;

    public FlowfitException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
