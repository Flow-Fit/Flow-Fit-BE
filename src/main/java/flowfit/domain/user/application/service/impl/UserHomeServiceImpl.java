package flowfit.domain.user.application.service.impl;


import flowfit.domain.ptrelation.domain.entity.ptrelation.PtRelation;
import flowfit.domain.ptrelation.domain.repository.PtRelationRepository;
import flowfit.domain.ptsession.domain.entity.PtSession;
import flowfit.domain.ptsession.domain.repository.PtSessionRepository;
import flowfit.domain.user.application.service.UserHomeService;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.repository.MemberRepository;
import flowfit.domain.user.domain.repository.TrainerRepository;
import flowfit.domain.user.infra.exception.*;
import flowfit.domain.user.presentation.dto.req.UserAlarmDto;
import flowfit.domain.user.presentation.dto.req.UserJoinDto;
import flowfit.domain.user.presentation.dto.req.UserLoginDto;
import flowfit.domain.user.presentation.dto.res.MemberCalendarResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserHomeServiceImpl implements UserHomeService {
    private final TrainerRepository trainerRepository;
    private final MemberRepository memberRepository;
    private final PtSessionRepository ptSessionRepository;
    private final PtRelationRepository ptRelationRepository;


    @Override
    public void userJoin(UserJoinDto joinDto, HttpServletResponse response) throws IOException {


    }

    @Override
    public void userLoin(UserLoginDto loginDto, HttpServletResponse response) throws IOException {

    }

    @Override
    public void userAlarm(UserAlarmDto dto, String userId) {
        Member member = memberRepository.findById(userId).orElse(null);

        if (member == null) {
            throw new UserNotFoundException("회원 정보가 없습니다.");
        }

        if (dto.alarm()) {
            member.updateAlarm(false);
        } else {
            member.updateAlarm(true);
        }
    }

    @Override
    public List<MemberCalendarResponse> userCalendarDate(int year, int month, int date, String userId) {
        Member member = memberRepository.findById(userId).orElse(null);

        if (member == null) {
            throw new UserNotFoundException("회원 정보가 없습니다.");
        }
        PtRelation ptRelation = ptRelationRepository.findByMember(member).orElse(null);
        if (ptRelation == null) {
            throw new UserNotFoundException("일치하는 PT 정보가 없습니다.");
        }

        List<PtSession> ptSessions = ptSessionRepository.findByPtRelationAndDate(ptRelation, year, month, date);

        return ptSessions.stream()
                .map(ptSession -> MemberCalendarResponse.of(
                        ptSession.getId(),
                        ptSession.getStartTime(),
                        ptSession.getEndTime(),
                        ptSession.getStatus(),
                        ptSession.getPtRelation().getTrainer().getName()
                )).toList();
    }

    @Override
    public List<MemberCalendarResponse> userCalendarMonth(int year, int month, String userId) {

        Member member = memberRepository.findById(userId).orElse(null);

        if (member == null) {
            throw new UserNotFoundException("회원 정보가 없습니다.");
        }
        PtRelation ptRelation = ptRelationRepository.findByMember(member).orElse(null);
        if (ptRelation == null) {
            throw new UserNotFoundException("일치하는 PT 정보가 없습니다.");
        }

        List<PtSession> ptSessions = ptSessionRepository.findByPtRelationAndDateAll(ptRelation, year, month);

        return ptSessions.stream()
                .map(ptSession -> MemberCalendarResponse.of(
                        ptSession.getId(),
                        ptSession.getStartTime(),
                        ptSession.getEndTime(),
                        ptSession.getStatus(),
                        ptSession.getPtRelation().getTrainer().getName()
                )).toList();
    }


}
