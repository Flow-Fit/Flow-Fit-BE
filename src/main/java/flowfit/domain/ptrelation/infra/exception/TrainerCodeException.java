package flowfit.domain.ptrelation.infra.exception;


import flowfit.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class TrainerCodeException extends FlowfitException {
    public TrainerCodeException() {
        super(HttpStatus.NOT_FOUND, "일치하는 트레이너 정보가 없습니다.");
    }

}
