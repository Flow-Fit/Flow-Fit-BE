package flowfit.domain.ptsession.application.service;

import flowfit.domain.ptsession.presentation.dto.res.UserIssueResponse;

public interface IssueService {

    void postIssue(String conflict, Long id);

    UserIssueResponse patchIssue(String conflict, Long id);
}
