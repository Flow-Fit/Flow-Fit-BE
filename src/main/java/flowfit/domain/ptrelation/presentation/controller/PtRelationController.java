package flowfit.domain.ptrelation.presentation.controller;

import flowfit.domain.ptrelation.application.service.PtRelationService;
import flowfit.domain.ptrelation.presentation.dto.req.PtRelationRequestDto;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.repository.MemberRepository;
import flowfit.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pt/relation")
@RequiredArgsConstructor
@Slf4j
public class PtRelationController {

    private final PtRelationService ptRelationService;

    // 회원 등록
    @PostMapping
    public void relationPost(@RequestBody PtRelationRequestDto dto, @AuthenticationPrincipal String userId) {
        ptRelationService.ptRelationSave(dto, userId);
    }



}
