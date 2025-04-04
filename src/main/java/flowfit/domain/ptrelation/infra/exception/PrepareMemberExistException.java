package flowfit.domain.ptrelation.infra.exception;


import flowfit.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class PrepareMemberExistException extends FlowfitException {
    public PrepareMemberExistException() {
        super(HttpStatus.NOT_FOUND, "이미 신청한 회원 입니다.");
    }

}
