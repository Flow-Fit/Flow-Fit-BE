package flowfit.domain.ptrelation.infra.exception;


import flowfit.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class PrepareTrainerExistException extends FlowfitException {
    public PrepareTrainerExistException() {
        super(HttpStatus.NOT_FOUND, "이미 신청한 트레이너 입니다.");
    }

}
