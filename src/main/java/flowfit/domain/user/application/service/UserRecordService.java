package flowfit.domain.user.application.service;

import flowfit.domain.user.presentation.dto.res.MemberCalendarResponse;
import flowfit.domain.user.presentation.dto.res.MemberRelationResponse;

import java.util.List;

public interface UserRecordService {

    List<MemberRelationResponse> userRelation(String userId);

    List<MemberCalendarResponse> userSessionAll(Long id);

    MemberCalendarResponse userSession(Long id);
}
