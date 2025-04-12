package flowfit.domain.user.infra.exception;

import flowfit.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class PtRelationNotFoundException extends FlowfitException {
  public PtRelationNotFoundException() {
    super(HttpStatus.NOT_FOUND, "해당 PT 계약이 존재하지 않습니다.");
  }
}
