package flowfit.domain.user.application.service;

import flowfit.domain.user.presentation.dto.res.MemberCalendarResponse;
import java.util.List;

public interface UserRecordService {



    List<MemberCalendarResponse> userSessionAll(Long id);

    MemberCalendarResponse userSession(Long id);
}
