package flowfit.domain.ptrelation.infra.exception;

import flowfit.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class RelationNotFoundException extends FlowfitException {
    public RelationNotFoundException() {
        super(HttpStatus.NOT_FOUND, "트레이너에 해당하는 관계가 없습니다.");
    }
}
