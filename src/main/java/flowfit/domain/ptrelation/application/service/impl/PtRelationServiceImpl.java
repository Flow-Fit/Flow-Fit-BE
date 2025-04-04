package flowfit.domain.ptrelation.application.service.impl;

import flowfit.domain.ptrelation.application.service.PtRelationService;
import flowfit.domain.ptrelation.domain.entity.prepare.PreparePt;
import flowfit.domain.ptrelation.domain.entity.ptrelation.PtFinal;
import flowfit.domain.ptrelation.domain.entity.ptrelation.PtRelation;
import flowfit.domain.ptrelation.domain.repository.PreparePtRepository;
import flowfit.domain.ptrelation.domain.repository.PtRelationRepository;
import flowfit.domain.ptrelation.infra.exception.PrepareMemberExistException;
import flowfit.domain.ptrelation.infra.exception.PrepareTrainerExistException;
import flowfit.domain.ptrelation.infra.exception.TrainerCodeException;
import flowfit.domain.ptrelation.presentation.dto.req.PtRelationRequestDto;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import flowfit.domain.user.domain.repository.MemberRepository;
import flowfit.domain.user.domain.repository.TrainerRepository;
import flowfit.domain.user.infra.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PtRelationServiceImpl implements PtRelationService {

    private final PtRelationRepository ptRelationRepository;
    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;
    private final PreparePtRepository preparePtRepository;

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
}
