package flowfit.domain.ptrelation.presentation.controller;

import flowfit.domain.ptrelation.application.service.PtRelationService;
import flowfit.domain.ptrelation.presentation.dto.req.PtRelationRequestDto;
import flowfit.domain.ptrelation.presentation.dto.res.MemberRelationResponse;
import flowfit.domain.ptrelation.presentation.dto.res.TrainerRelationDetailResponse;
import flowfit.domain.ptrelation.presentation.dto.res.TrainerRelationResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relation")
@RequiredArgsConstructor
@Slf4j
public class PtRelationController {

    private final PtRelationService ptRelationService;

    // 회원 등록
    @PostMapping
    public void relationPost(@RequestBody PtRelationRequestDto dto, @AuthenticationPrincipal String userId) {
        ptRelationService.ptRelationSave(dto, userId);
    }

    // 회원이 선택한 트레이너 조회(릴레이션 PT)
    @GetMapping("/trainers")
    public ResponseEntity<List<MemberRelationResponse>> relationsByMember(@AuthenticationPrincipal String userId) {
        List<MemberRelationResponse> res = ptRelationService.userRelation(userId);
        return ResponseEntity.status(200).body(res);
    }

    // 트레이너의 회원들 조회(릴레이션 PT)
    @SecurityRequirement(name = "JWT")
    @GetMapping("/members")
    public ResponseEntity<List<TrainerRelationResponse>> relationsByTrainer(
            @AuthenticationPrincipal String userId) {
        List<TrainerRelationResponse> res = ptRelationService.trainerRelation(userId);
        return ResponseEntity.status(200).body(res);
    }

    // 특정 PT 관계 상세 조회 (id 기준)
    @GetMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<TrainerRelationDetailResponse> relationByTrainer(
            @AuthenticationPrincipal String userId,
            @PathVariable Long id) {
        TrainerRelationDetailResponse res = ptRelationService.trainerRelationDetail(userId, id);
        return ResponseEntity.ok(res);
    }
}
