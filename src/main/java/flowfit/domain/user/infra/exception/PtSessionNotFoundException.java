package flowfit.domain.user.infra.exception;

import flowfit.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class PtSessionNotFoundException extends FlowfitException {
  public PtSessionNotFoundException() {
    super(HttpStatus.NOT_FOUND, "해당 PT 세션이 존재하지 않습니다.");
  }
}
