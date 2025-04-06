package flowfit.domain.user.application.service.impl;

import flowfit.domain.ptrelation.domain.entity.ptrelation.PtRelation;
import flowfit.domain.ptrelation.domain.repository.PtRelationRepository;
import flowfit.domain.ptsession.domain.entity.PtSession;
import flowfit.domain.ptsession.domain.repository.PtSessionRepository;
import flowfit.domain.user.application.service.UserRecordService;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.repository.MemberRepository;
import flowfit.domain.user.infra.exception.UserNotFoundException;
import flowfit.domain.user.presentation.dto.res.MemberCalendarResponse;
import flowfit.domain.user.presentation.dto.res.MemberRelationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRecordServiceImpl implements UserRecordService {
    private final PtRelationRepository ptRelationRepository;
    private final MemberRepository memberRepository;
    private final PtSessionRepository ptSessionRepository;

    @Override
    public List<MemberRelationResponse> userRelation(String userId) {

        Member member = memberRepository.findById(userId).orElse(null);

        if (member == null) {
            throw new UserNotFoundException("회원 정보가 없습니다.");
        }
        List<PtRelation> allByMember = ptRelationRepository.findAllByMember(member);

        List<MemberRelationResponse> memberRelationResponses = new ArrayList<>();
        for (PtRelation ptRelation : allByMember) {
            MemberRelationResponse res = MemberRelationResponse.of(ptRelation.getId(), ptRelation.getTrainer().getName(), ptRelation.getTrainer().getTrainerCode(),
                    ptRelation.getPtStartDate(), ptRelation.getPtLastDate(), ptRelation.getPtFinal());

            memberRelationResponses.add(res);
        }
        return memberRelationResponses;
    }

    @Override
    public List<MemberCalendarResponse> userSessionAll(Long id) {
        // id는 relation 테이블의 pk 임
        PtRelation ptRelation = ptRelationRepository.findById(id).orElse(null);
        if (ptRelation == null) {
            throw new UserNotFoundException("일치하는 Pt 정보가 없습니다.");
        }
        List<PtSession> allByPtRelation = ptSessionRepository.findAllByPtRelation(ptRelation);

        List<MemberCalendarResponse> memberCalendarResponses = new ArrayList<>();

        for (PtSession ptSession : allByPtRelation) {
            MemberCalendarResponse res = MemberCalendarResponse.of(ptSession.getId(), ptSession.getStartTime(), ptSession.getEndTime(), ptSession.getStatus(), ptSession.getPtRelation().getTrainer().getName());
            memberCalendarResponses.add(res);
        }

        return memberCalendarResponses;
    }

    @Override
    public MemberCalendarResponse userSession(Long id) {
        PtSession ptSession = ptSessionRepository.findById(id).orElse(null);

        if (ptSession == null) {
            throw new UserNotFoundException("일치하는 세션 정보가 없습니다.");
        }

        return MemberCalendarResponse.of(id, ptSession.getStartTime(), ptSession.getEndTime(), ptSession.getStatus(), ptSession.getPtRelation().getTrainer().getName());

    }
}
