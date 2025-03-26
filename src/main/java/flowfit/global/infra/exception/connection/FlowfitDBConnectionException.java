package flowfit.global.infra.exception.connection;

import flowfit.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class FlowfitDBConnectionException extends FlowfitException {
    public FlowfitDBConnectionException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "DB Connection 과정 중 문제가 발생했습니다.");
    }
}
