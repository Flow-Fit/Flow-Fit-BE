package flowfit.domain.ptrelation.infra.exception;

import flowfit.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class RelationAccessException extends FlowfitException {
    public RelationAccessException() {
        super(HttpStatus.FORBIDDEN, "해당 관계에 접근 권한이 없습니다.");
    }
}
