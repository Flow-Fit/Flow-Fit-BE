package flowfit.domain.ptsession.presentation.controller;

import flowfit.domain.ptsession.application.service.IssueService;
import flowfit.domain.ptsession.presentation.dto.req.UserIssueDto;
import flowfit.domain.ptsession.presentation.dto.res.UserIssueResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/issue/{id}")
@RequiredArgsConstructor
@Slf4j
public class IssueController {

    private final IssueService issueService;

    @PostMapping
    public void userIssuePost(@PathVariable Long id, @RequestBody UserIssueDto dto) {
        issueService.postIssue(dto.conflict(), id);
    }

    @PatchMapping
    public ResponseEntity<UserIssueResponse> userIssuePatch(@PathVariable Long id, @RequestBody UserIssueDto dto) {
        return ResponseEntity.status(200).body(issueService.patchIssue(dto.conflict(), id));
    }
}
