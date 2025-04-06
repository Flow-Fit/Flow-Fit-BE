package flowfit.domain.user.presentation.controller;

import flowfit.domain.user.application.service.UserRecordService;
import flowfit.domain.user.presentation.dto.res.MemberCalendarResponse;
import flowfit.domain.user.presentation.dto.res.MemberRelationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserRecordController {
    private final UserRecordService userRecordService;


    // 회원이 선택한 트레이너 조회(릴레이션 PT)
    @GetMapping("/relation")
    public ResponseEntity<List<MemberRelationResponse>> userRelation(@AuthenticationPrincipal String userId) {
        List<MemberRelationResponse> res = userRecordService.userRelation(userId);
        return ResponseEntity.status(200).body(res);
    }

    // 회원이 선택한 트레이너의 세션들 조회(릴레이션 PT)
    @GetMapping("/session/all")
    public ResponseEntity<List<MemberCalendarResponse>> userSessionAll(@RequestParam Long id) {
        List<MemberCalendarResponse> res = userRecordService.userSessionAll(id);

        return ResponseEntity.status(200).body(res);
    }


    // 회원이 선택한 트레이너의 세션들 중 세션 조회(릴레이션 PT)
    @GetMapping("/session")
    public ResponseEntity<MemberCalendarResponse> userSession(@RequestParam Long id) {
        MemberCalendarResponse res = userRecordService.userSession(id);

        return ResponseEntity.status(200).body(res);
    }

}