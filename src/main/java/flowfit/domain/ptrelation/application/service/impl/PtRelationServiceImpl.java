package flowfit.domain.ptrelation.application.service.impl;

import flowfit.domain.ptrelation.application.service.PtRelationService;
import flowfit.domain.ptrelation.domain.entity.prepare.PreparePt;
import flowfit.domain.ptrelation.domain.entity.ptrelation.PtFinal;
import flowfit.domain.ptrelation.domain.entity.ptrelation.PtRelation;
import flowfit.domain.ptrelation.domain.repository.PreparePtRepository;
import flowfit.domain.ptrelation.domain.repository.PtRelationRepository;
import flowfit.domain.ptrelation.infra.exception.PrepareMemberExistException;
import flowfit.domain.ptrelation.infra.exception.RelationNotFoundException;
import flowfit.domain.ptrelation.infra.exception.TrainerCodeException;
import flowfit.domain.ptrelation.presentation.dto.req.PtRelationRequestDto;
import flowfit.domain.ptrelation.presentation.dto.res.TrainerRelationDetailResponse;
import flowfit.domain.ptrelation.presentation.dto.res.TrainerRelationResponse;
import flowfit.domain.ptsession.domain.entity.PtSession;
import flowfit.domain.ptsession.domain.entity.Status;
import flowfit.domain.ptsession.domain.repository.PtSessionRepository;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import flowfit.domain.user.domain.repository.MemberRepository;
import flowfit.domain.user.domain.repository.TrainerRepository;
import flowfit.domain.user.infra.exception.UserNotFoundException;
import flowfit.domain.ptrelation.presentation.dto.res.MemberRelationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PtRelationServiceImpl implements PtRelationService {

    private final PtRelationRepository ptRelationRepository;
    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;
    private final PreparePtRepository preparePtRepository;
    private final PtSessionRepository ptSessionRepository;

    @Override
    public void ptRelationSave(PtRelationRequestDto dto, String userId) {
        Trainer trainer = trainerRepository.findById(userId).orElse(null);

        if (trainer == null) {
            throw new TrainerCodeException();
        }
        Member member = memberRepository.findByPhoneNumber(dto.phoneNumber()).orElse(null);

        if (member == null) {
            throw new UserNotFoundException("일치하는 회원 정보가 없습니다.");
        }

        PreparePt findPreparePt = preparePtRepository.findByTrainerAndMember(trainer, member).orElse(null);

        if (findPreparePt != null) {
            preparePtRepository.delete(findPreparePt);
        }

        PtRelation findPtRelation = ptRelationRepository.findByTrainerAndMember(trainer, member).orElse(null);

        if(findPtRelation != null){
            throw new PrepareMemberExistException();
        }

        PtRelation ptRelation = PtRelation
                .builder()
                .trainer(trainer)
                .member(member)
                .ptStartDate(dto.ptStartDate())
                .ptLastDate(dto.ptLastDate())
                .ptFinal(PtFinal.PREPARE)
                .totalMoney(dto.totalMoney())
                .session(dto.session())
                .build();

        ptRelationRepository.save(ptRelation);
    }

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
    public List<TrainerRelationResponse> trainerRelation(String userId){
        Trainer trainer = trainerRepository.findById(userId).orElse(null);

        if (trainer == null) {
            throw new UserNotFoundException("트레이너 정보가 없습니다.");
        }
        List<PtRelation> allByTrainer = ptRelationRepository.findAllByTrainer(trainer);

        List<TrainerRelationResponse> trainerRelationResponses = new ArrayList<>();

        for (PtRelation ptRelation : allByTrainer) {
            TrainerRelationResponse res = TrainerRelationResponse.of(
                    ptRelation.getId(),
                    ptRelation.getMember().getName(),
                    // ptRelation.getAlias(),
                    ptRelation.getPtStartDate(),
                    ptRelation.getPtLastDate(),
                    ptRelation.getTotalMoney(),
                    ptRelation.getSession(),
                    ptRelation.getPtFinal());

            trainerRelationResponses.add(res);
        }
        return trainerRelationResponses;
    }

    @Override
    public TrainerRelationDetailResponse trainerRelationDetail(String userId, Long relationId) {
        Trainer trainer = trainerRepository.findById(userId).orElse(null);

        if (trainer == null) {
            throw new UserNotFoundException("트레이너 정보가 없습니다.");
        }

        PtRelation ptRelation = ptRelationRepository.findByIdAndTrainer(relationId, trainer).orElse(null);

        // ptRelation이 없을 경우 에러
        if (ptRelation == null) {
            throw new RelationNotFoundException();
        }

        // 사용된 PT 세션 수 계산 (Status가 COMPLETED인 세션만 계산)
        List<PtSession> completedSessions = ptSessionRepository.findByPtRelationAndStatus(ptRelation, Status.COMPLETED);
        int usedSessions = completedSessions.size();

        // 총 PT 횟수와 잔여 PT, 단가 계산
        int totalSession = ptRelation.getSession();
        int remainingPt = totalSession - usedSessions;
        int unitPrice = totalSession != 0 ? ptRelation.getTotalMoney() / totalSession : 0;

        return TrainerRelationDetailResponse.of(
                ptRelation.getId(),
                ptRelation.getMember().getName(),
                ptRelation.getMember().getPhoneNumber(),   // 회원 연락처
                ptRelation.getMember().getBirthDate(),   // 회원 생년월일 (LocalDate 타입)
                // ptRelation.getAlias(),                   // 별명
                ptRelation.getPtStartDate(),             // 등록일
                ptRelation.getPtLastDate(),              // 만료일
                remainingPt,                           // 잔여 PT
                totalSession,                          // 총 PT 횟수
                unitPrice,                             // 단가정보
                ptRelation.getTotalMoney(),            // 세션 총 금액
                ptRelation.getPtFinal()                // PT 최종 상태
        );
    }
}
