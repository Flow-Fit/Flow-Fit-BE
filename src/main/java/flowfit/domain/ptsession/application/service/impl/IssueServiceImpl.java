package flowfit.domain.ptsession.application.service.impl;

import flowfit.domain.ptsession.application.service.IssueService;
import flowfit.domain.ptsession.domain.entity.PtSession;
import flowfit.domain.ptsession.domain.entity.Status;
import flowfit.domain.ptsession.domain.repository.PtSessionRepository;
import flowfit.domain.ptsession.presentation.dto.res.UserIssueResponse;
import flowfit.domain.user.infra.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final PtSessionRepository ptSessionRepository;

    @Override
    public void postIssue(String conflict, Long id) {
        PtSession ptSession = ptSessionRepository.findById(id).orElse(null);

        if(ptSession == null){
            throw new UserNotFoundException("일치하는 세션 정보가 없습니다."); // 나중에 에러 커스텀 할게요;;; 귀찮
        }

        ptSession.updateConflict(conflict);
        ptSession.updateStatus(Status.COMPLETED); // 이의제기 됐으니깐 완료
    }

    @Override
    public UserIssueResponse patchIssue(String conflict, Long id) {
        PtSession ptSession = ptSessionRepository.findById(id).orElse(null);
        if(ptSession == null){
            throw new UserNotFoundException("일치하는 세션 정보가 없습니다."); // 나중에 에러 커스텀 할게요;;; 귀찮
        }

        ptSession.updateConflict(conflict);
        return UserIssueResponse.of(conflict);

    }
}
